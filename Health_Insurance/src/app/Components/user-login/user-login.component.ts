import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-login',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {
  isSignUp = false;
  forgotMode = false;
  resetMode = false;

  signInForm: FormGroup;
  signUpForm: FormGroup;
  forgotPasswordForm: FormGroup;
  resetPasswordForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {
    this.signInForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });

    this.signUpForm = this.fb.group({
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    });

    this.forgotPasswordForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });

    this.resetPasswordForm = this.fb.group({
      token: ['', Validators.required],
      newPassword: ['', Validators.required],
      confirmNewPassword: ['', Validators.required]
    });
  }

  toggleForm() {
    this.isSignUp = !this.isSignUp;
    this.forgotMode = false;
    this.resetMode = false;
  }

  enableForgotMode() {
    this.forgotMode = true;
    this.resetMode = false;
    this.isSignUp = false;
  }

  enableResetMode() {
    this.resetMode = true;
    this.forgotMode = false;
    this.isSignUp = false;
  }

  onSignIn() {
    if (this.signInForm.valid) {
      this.http.post('http://localhost:8080/api/users/login', this.signInForm.value, { responseType: 'text' })
        .subscribe({
          next: (res) => {
            alert('✅ Login successful!');
            this.router.navigate(['/user-dashboard']);
          },
          error: (err) => {
            console.error('Login error:', err);
            if (err.status === 403) {
              alert('❌ Login failed. Please check your credentials or verify your email.');
            } else {
              alert('❌ Login failed. Please try again later.');
            }
          }
        });
    } else {
      alert('❌ Please fill all required fields correctly!');
    }
  }

  onSignUp() {
    if (this.signUpForm.valid) {
      const data = {
        ...this.signUpForm.value,
        accountType: 'INDIVIDUAL'
      };

      this.http.post('http://localhost:8080/api/users/register', data, { responseType: 'text' })
        .subscribe({
          next: (res) => {
            alert('✅ Registration successful! Please check your email.');
            console.log('Register response:', res);
            this.toggleForm();
          },
          error: (err) => {
            console.error('Registration error:', err);
            alert('❌ Registration failed. Try again.');
          }
        });
    } else {
      alert('❌ Please fill all required Sign Up fields correctly!');
    }
  }

  onForgotPassword() {
    if (this.forgotPasswordForm.valid) {
      const formData = new FormData();
      formData.append('email', this.forgotPasswordForm.value.email);

      this.http.post('http://localhost:8080/api/users/forgot-password', formData, { responseType: 'text' })
        .subscribe({
          next: (res) => {
            alert('📩 OTP/Token sent to your registered email.');
            this.enableResetMode();
          },
          error: (err) => {
            console.error('Forgot password error:', err);
            alert('❌ Could not send token. Please check email.');
          }
        });
    }
  }

  onResetPassword() {
    if (this.resetPasswordForm.valid) {
      this.http.post('http://localhost:8080/api/users/reset-password', this.resetPasswordForm.value, { responseType: 'text' })
        .subscribe({
          next: (res) => {
            alert('🔐 Password reset successfully!');
            // Reset all form modes to show sign-in form
            this.isSignUp = false;
            this.forgotMode = false;
            this.resetMode = false;
            // Optionally reset the forms
            this.resetPasswordForm.reset();
            this.signInForm.reset();
          },
          error: (err) => {
            console.error('Reset password error:', err);
            alert('❌ Password reset failed. Check token or try again.');
          }
        });
    }
  }
}



