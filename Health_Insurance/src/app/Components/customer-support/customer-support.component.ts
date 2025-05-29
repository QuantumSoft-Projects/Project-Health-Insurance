import { AfterViewInit, Component, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient, HttpClientModule, HttpHeaders, HttpParams } from '@angular/common/http';
import { CommonModule } from '@angular/common';
 
 
@Component({
  selector: 'app-customer-support',
  standalone: true,
  imports: [ReactiveFormsModule, HttpClientModule,CommonModule ,FormsModule],
  templateUrl: './customer-support.component.html',
  styleUrl: './customer-support.component.css'
})
export class CustomerSupportComponent implements AfterViewInit {
   supportForm!: FormGroup;
  submittedTicketId: number = 0;
  selectedTicketId: number = 0;
  authToken: string = '';
  isAdmin: boolean = false;

  assignTicketId!: number;
  assignedTo!: string;
  statusTicketId!: number;
  newStatus!: string;

  tickets: any[] = [];
  userTickets: any[] = [];

  constructor(
    private el: ElementRef,
    private fb: FormBuilder,
    private http: HttpClient
  ) {
    this.supportForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      policy: [''],
      queryType: ['general'],
      message: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    // Load JWT and role (admin check)
    this.authToken = localStorage.getItem('authToken') || '';
    const role = localStorage.getItem('role');
    this.isAdmin = role === 'ADMIN';
  }

  ngAfterViewInit(): void {
    const faqItems = this.el.nativeElement.querySelectorAll('.faq-item');
    faqItems.forEach((item: HTMLElement) => {
      item.addEventListener('click', () => {
        faqItems.forEach((el: HTMLElement) => {
          if (el !== item) el.classList.remove('active');
        });
        item.classList.toggle('active');
      });
    });
  }

  onSubmit() {
    if (this.supportForm.valid) {
      const form = this.supportForm.value;
      const supportTicketPayload = {
        userId: 1, // Replace with actual user ID
        issueType: form.queryType,
        description: form.message,
        priority: 'HIGH'
      };

      this.http.post('http://localhost:8080/api/support-tickets', supportTicketPayload)
        .subscribe({
          next: (res: any) => {
            alert('Your query has been submitted!');
            this.submittedTicketId = res.id;
            this.supportForm.reset();
          },
          error: (err) => {
            console.error('Error submitting ticket:', err);
            alert('Submission failed. Try again.');
          }
        });
    } else {
      alert('Please fill out all required fields.');
    }
  }

  assignTicketToAgent() {
    if (!this.assignTicketId || !this.assignedTo) {
      alert('Please enter both ticket ID and agent name.');
      return;
    }

    if (!this.authToken) {
      alert('You must be logged in as admin.');
      return;
    }

    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.authToken);
    const params = new HttpParams().set('assignedTo', this.assignedTo);

    this.http.put(`http://localhost:8080/api/support-tickets/${this.assignTicketId}/assign`, null, { headers, params })
      .subscribe({
        next: () => alert('Ticket assigned successfully.'),
        error: err => {
          console.error('Assign error:', err);
          alert('Assign failed: ' + (err.error?.message || err.statusText));
        }
      });
  }

  updateTicketStatus() {
    if (!this.statusTicketId || !this.newStatus) {
      alert('Please enter both ticket ID and status.');
      return;
    }

    if (!this.authToken) {
      alert('You must be logged in as admin.');
      return;
    }

    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.authToken);
    const params = new HttpParams().set('status', this.newStatus);

    this.http.put(`http://localhost:8080/api/support-tickets/${this.statusTicketId}/status`, null, { headers, params })
      .subscribe({
        next: () => {
          alert('Status updated successfully.');
          this.fetchTicketsByAdmin();
        },
        error: err => {
          console.error('Status update error:', err);
          alert('Update failed: ' + (err.error?.message || err.statusText));
        }
      });
  }

  fetchTicketsByAdmin() {
    if (!this.authToken) {
      alert('You must be logged in as admin.');
      return;
    }

    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.authToken);

    this.http.get(`http://localhost:8080/api/support-tickets/admin`, { headers })
      .subscribe({
        next: (tickets: any) => {
          this.tickets = tickets;
        },
        error: err => {
          console.error('Admin fetch error:', err);
          alert('Failed to fetch tickets: ' + err.statusText);
        }
      });
  }

  fetchTicketsByUser(userId: number) {
    this.http.get(`http://localhost:8080/api/support-tickets/user/${userId}`)
      .subscribe({
        next: (tickets: any) => {
          this.userTickets = tickets;
        },
        error: err => {
          console.error('User fetch error:', err);
          alert('Failed to fetch your tickets: ' + err.statusText);
        }
      });
  }
}