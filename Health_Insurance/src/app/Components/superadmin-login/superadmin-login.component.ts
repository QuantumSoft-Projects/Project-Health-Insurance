import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router'; // <-- Import Router

@Component({
  selector: 'app-superadmin-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './superadmin-login.component.html',
  styleUrl: './superadmin-login.component.css'
})
export class SuperadminLoginComponent {
  username = '';
  password = '';

  constructor(private http: HttpClient, private router: Router) {} // <-- Inject Router
  loginSuperadmin() {

    const data = {
  
      username: this.username,
  
      password: this.password,
  
    };
   
    this.http.post('http://localhost:8080/api/superadmin/login', data, {
  
      responseType: 'text'
  
    }).subscribe(
  
      response => {
  
        console.log('Login success:', response); // response is the JWT
  
        localStorage.setItem('token', response); // Store token
  
        window.alert('Login successful!');
  
        this.router.navigate(['/superadminDashboard']);
  
      },
  
      error => {
  
        console.error('Login failed', error);
  
        window.alert('Login failed. Please check your username and password.');
  
      }
  
    );
  
  }
  
   
}
