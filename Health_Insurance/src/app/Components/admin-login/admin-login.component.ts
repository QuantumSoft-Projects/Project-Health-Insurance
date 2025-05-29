import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router'; // ✅ Step 1: Import Router

@Component({
  selector: 'app-admin-login',
  standalone: true,
  imports: [FormsModule, HttpClientModule, CommonModule],
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent {
  email = '';
  password = '';
  forgotEmail = '';
  otp = '';
  newPassword = '';
  confirmPassword = '';
  showForgotPassword = false;
  showResetPassword = false;

  constructor(private http: HttpClient, private router: Router) {} // ✅ Step 2: Inject Router

  toggleForgotPassword() {
    this.showForgotPassword = !this.showForgotPassword;
  }

  loginAdmin() {
    const formData = new FormData();
    formData.append('email', this.email);
    formData.append('password', this.password);

    this.http.post('http://localhost:8080/api/admin/login', formData, {
      responseType: 'text' as 'json'
    }).subscribe(
      (response) => {
        console.log('Admin login success:', response);
        window.alert('Login successful!');
        this.router.navigate(['/admin-dashboard']); 
        // Navigate to dashboard
      },
      (error) => {
        console.error('Login failed:', error);
        window.alert('Login failed. ' + (error.error?.text || 'Please check your credentials.'));
      }
    );
  }

  sendOtp() {
    const email = this.forgotEmail.trim();

    this.http.patch(`http://localhost:8080/api/admin/sendOtp?email=${email}`, {}, {
      responseType: 'text' as 'json'
    }).subscribe(
      (response) => {
        console.log('OTP sent:', response);
        window.alert('OTP has been sent to your email.');
        this.showResetPassword = true;
      },
      (error) => {
        console.error('OTP send failed:', error);
        window.alert('Failed to send OTP. Please try again.');
      }
    );
  }

  resetPassword() {
    const otp = this.otp;
    const password = this.newPassword;
    const confirmPassword = this.confirmPassword;

    const url = `http://localhost:8080/api/admin/resetPswd?otp=${otp}&password=${password}&confirmPassword=${confirmPassword}`;

    this.http.patch(url, {}, {
      responseType: 'text' as 'json'
    }).subscribe(
      (response) => {
        console.log('Password reset successful:', response);
        window.alert('Password has been successfully reset!');
        this.router.navigate(['/admin-login']); // ✅ Step 3: Redirect to login
      },
      (error) => {
        console.error('Password reset failed:', error);
        window.alert('Password reset failed. Please try again.');
      }
    );
  }
}
