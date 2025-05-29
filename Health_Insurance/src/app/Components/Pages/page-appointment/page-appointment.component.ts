import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
 
@Component({
  selector: 'app-page-appointment',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './page-appointment.component.html',
  styleUrls: ['./page-appointment.component.css']
})
export class PageAppointmentComponent {
  appointmentForm: FormGroup;
  isSubmitted = false;
  isSubmitting = false;
 
  constructor(private fb: FormBuilder) {
    this.appointmentForm = this.fb.group({
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', Validators.required],
      insurancePlan: ['', Validators.required],
      preferredDoctor: [''],
      appointmentDate: ['', Validators.required],
      preferredTime: ['', Validators.required],
      additionalNotes: ['']
    });
  }
 
  onSubmit() {
    if (this.appointmentForm.invalid) {
      this.appointmentForm.markAllAsTouched();
      return;
    }
 
    this.isSubmitting = true;
 
    // Simulate API call
    setTimeout(() => {
      this.isSubmitted = true;
      this.isSubmitting = false;
      console.log('Appointment Booked:', this.appointmentForm.value);
      this.appointmentForm.reset(); // Optional: reset the form after submission
    }, 1500);
  }
}
 
 