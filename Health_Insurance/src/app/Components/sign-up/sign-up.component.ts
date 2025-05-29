import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {
  signupForm!: FormGroup;
  
    constructor(private fb: FormBuilder, private router: Router) {
      this.signupForm = this.fb.group({
        fullName: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(6)]],
        policyNumber: ['', Validators.required]
      });
    }
  
    onSignup(): void {
      if (this.signupForm.valid) {
        console.log('User Signed Up:', this.signupForm.value);
        // Backend call here (e.g. via HTTP or Firebase)
        this.signupForm.reset();
      } else {
        this.signupForm.markAllAsTouched();
      }
    }
  
    closeSignup() {
      // Example: navigate away or hide the form
      this.router.navigate(['/']); // or any route you prefer
    }
}
