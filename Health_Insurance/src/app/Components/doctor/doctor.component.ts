import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Doctor } from '../../model/doctor';
 
@Component({
  selector: 'app-doctor',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css']
})
export class DoctorComponent implements OnInit {
  private http = inject(HttpClient);
  private fb = inject(FormBuilder);
 
  doctors: Doctor[] = [];
  selectedDoctor: Doctor | null = null;
 
  doctorForm = this.fb.group({
    name: ['', Validators.required],
    specialization: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    phone: ['', Validators.required],
    available: [true, Validators.required],
    hospitalId: ['', Validators.required]
  });
 
  private getToken(): string {
    const token = localStorage.getItem('token'); // Replaced with your storage key
    return token ? `Bearer ${token}` : '';
  }
 
  private getAuthHeaders(): HttpHeaders {
    return new HttpHeaders({
      Authorization: this.getToken()
    });
  }
 
  ngOnInit() {
    this.getAllDoctors();
  }
 
  getAllDoctors() {
    this.http.get<Doctor[]>('http://localhost:8080/api/doctors').subscribe({
      next: data => this.doctors = data,
      error: err => console.error(err)
    });
  }
 
  getDoctorById(id: number) {
    if (id) {
    this.http.get<Doctor>(`http://localhost:8080/api/doctors/${id}`).subscribe({
      next: data => this.selectedDoctor = data,
      error: err => console.error(err)
    });
  }}
 
  createDoctor() {
    const hospitalId = Number(this.doctorForm.value.hospitalId);
    const body: Doctor = {
      name: this.doctorForm.value.name || '',
      specialization: this.doctorForm.value.specialization || '',
      email: this.doctorForm.value.email || '',
      phone: this.doctorForm.value.phone || '',
      available: this.doctorForm.value.available ?? true,
      hospital: { hospitalId }, // Wrap hospitalId inside the hospital object
    };
 
    this.http.post('http://localhost:8080/api/hospitals/' + hospitalId + '/doctors', body, {
      headers: this.getAuthHeaders()
    }).subscribe({
      next: _ => {
        this.getAllDoctors();
        this.doctorForm.reset();
      },
      error: err => console.error(err)
    });
  }
 
  updateDoctor(id: number) {
    const hospitalId = Number(this.doctorForm.value.hospitalId);
    const body: Doctor = {
      name: this.doctorForm.value.name || '',
      specialization: this.doctorForm.value.specialization || '',
      email: this.doctorForm.value.email || '',
      phone: this.doctorForm.value.phone || '',
      available: this.doctorForm.value.available ?? true,
      hospital: { hospitalId }, // Wrap hospitalId inside the hospital object
    };
 
    this.http.put(`http://localhost:8080/api/doctors/${id}`, body, {
      headers: this.getAuthHeaders()
    }).subscribe({
      next: _ => this.getAllDoctors(),
      error: err => console.error(err)
    });
  }
 
  deleteDoctor(id: number) {
    if (id) {
    this.http.delete(`http://localhost:8080/api/doctors/${id}`, {
      headers: this.getAuthHeaders()
    }).subscribe({
      next: _ => this.getAllDoctors(),
      error: err => console.error(err)
    });
  }}
 
  populateForm(doctor: any) {
    this.doctorForm.patchValue({
      name: doctor.name,
      specialization: doctor.specialization,
      email: doctor.email,
      phone: doctor.phone,
      available: doctor.available,
      hospitalId: doctor.hospital?.hospitalId || ''
    });
  }
}
 
 
 