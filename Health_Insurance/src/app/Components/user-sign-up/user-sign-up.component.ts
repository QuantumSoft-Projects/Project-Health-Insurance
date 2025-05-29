import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';



@Component({
  selector: 'app-user-sign-up',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './user-sign-up.component.html',
  styleUrl: './user-sign-up.component.css'
})
export class UserSignUpComponent implements OnInit {

   // Declare the user object with the required properties
   user = {
    fullName: '',
    email: '',
    phoneNumber: '',
    password: '',
    confirmPassword: '',
    accountType: 'INDIVIDUAL' // Default value
  };

  constructor() {}

  ngOnInit(): void {
    // You can initialize anything here if needed
  }

  openSignupModal() {
    const signupModalElement = document.getElementById('signupModal');
    if (signupModalElement) {
      const signupModal = new (window as any).bootstrap.Modal(signupModalElement);
      signupModal.show();
    }
  }

  closeSignupModal() {
    const signupModalElement = document.getElementById('signupModal');
    if (signupModalElement) {
      const signupModal = (window as any).bootstrap.Modal.getInstance(signupModalElement);
      signupModal?.hide();
    }
  }
   // Method to handle user registration
   registerUser() {
    console.log(this.user);  // Replace with actual registration logic
    // Here you can call your API to register the user
  }
}