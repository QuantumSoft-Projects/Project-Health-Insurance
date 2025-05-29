import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-appointment',
  standalone: true,
  imports: [FormsModule,HttpClientModule,ReactiveFormsModule],
  templateUrl: './appointment.component.html',
  styleUrl: './appointment.component.css'
})
export class AppointmentComponent {
  appointmentForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.appointmentForm = this.fb.group({
      appointmentDateTime: ['', Validators.required],
      concern: ['', Validators.required],
      userId: ['', Validators.required],
      doctorId: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.appointmentForm.invalid) return;

    const formData = this.appointmentForm.value;
    const payload = {
      appointmentDateTime: formData.appointmentDateTime,
      concern: formData.concern,
      status: 'PENDING',
      user: {
        userId: formData.userId
      },
      doctor: {
        doctorId: formData.doctorId
      }
    };

    this.http.post('http://localhost:8080/api/appointments/book', payload).subscribe({
      next: res => alert('Appointment booked successfully!'),
      error: err => console.error('Booking failed', err)
    });
  }


  // Method to confirm an appointment by HOSPITALADMIN (PUT request)
  confirmAppointment(appointmentId: number, consultationLink: string) {
    const payload = {
      consultationLink: consultationLink
    };

    this.http.put(`http://localhost:8080/api/appointments/confirm/${appointmentId}`, payload).subscribe({
      next: res => alert('Appointment confirmed successfully!'),
      error: err => console.error('Confirmation failed', err)
    });
  }

  // Method to fetch appointment by userId (GET request)
  fetchAppointmentsByUser(userId: number) {
    this.http.get(`http://localhost:8080/api/appointments/user/${userId}`).subscribe({
      next: (appointments: any) => {
        console.log('Fetched appointments:', appointments);
      },
      error: err => console.error('Failed to fetch appointments', err)
    });
  }

}
