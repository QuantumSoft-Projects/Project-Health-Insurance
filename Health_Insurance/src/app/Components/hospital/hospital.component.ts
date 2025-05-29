import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-hospital',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule
  ],
  templateUrl: './hospital.component.html',
  styleUrls: ['./hospital.component.css']
})
export class HospitalComponent implements OnInit {
  hospitalData = {
    name: '',
    address: '',
    city: '',
    state: '',
    postalCode: '',
    contactNumber: '',
    email: '',
    website: '',
    isNetworkHospital: false,
    icuAvailable: false,
    availableBeds: 0,
    isActive: true,
    specialties: [] as string[]
  };

  specialtyInput = '';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {}

  addSpecialty(): void {
    const s = this.specialtyInput.trim();
    if (s && !this.hospitalData.specialties.includes(s)) {
      this.hospitalData.specialties.push(s);
    }
    this.specialtyInput = '';
  }

  removeSpecialty(s: string): void {
    this.hospitalData.specialties = this.hospitalData.specialties.filter(x => x !== s);
  }

  addHospital(): void {
    const token = localStorage.getItem('token');
    if (!token) {
      alert('Not logged in');
      return;
    }

    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    this.http
      .post<any>('http://localhost:8080/api/hospitals', this.hospitalData, { headers })
      .subscribe({
        next: () => {
          alert('Hospital added successfully');
          // reset form
          this.hospitalData = {
            name: '',
            address: '',
            city: '',
            state: '',
            postalCode: '',
            contactNumber: '',
            email: '',
            website: '',
            isNetworkHospital: false,
            icuAvailable: false,
            availableBeds: 0,
            isActive: true,
            specialties: []
          };
        },
        error: err => {
          console.error('Error adding hospital', err);
          if (err.status === 403) {
            alert('Access denied: SUPERADMIN only');
          } else {
            alert('Failed to add hospital.');
          }
        }
      });
  }
}
