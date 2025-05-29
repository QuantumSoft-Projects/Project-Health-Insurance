import { Component } from '@angular/core';

import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';

import { FormsModule } from '@angular/forms';

import { RouterModule } from '@angular/router';

@Component({

  selector: 'app-admin-registration',

  standalone: true,

  imports: [FormsModule, RouterModule,HttpClientModule],

  templateUrl: './admin-registration.component.html',

  styleUrls: ['./admin-registration.component.css']

})

export class AdminRegistrationComponent {

  newAdmin = {

    name: '',

    email: '',

    password: '',

    role: '' // Optional: role preset if needed in frontend

  };

  constructor(private http: HttpClient) {}

  registerAdmin() {

    const token = localStorage.getItem('token'); // Assumes token is saved after superadmin login

    if (!token) {

      alert('Not authorized. Please log in as Superadmin.');

      return;

    }

    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    this.http.post('http://localhost:8080/api/admin/register', this.newAdmin, {
      headers,
      responseType: 'text' // <-- Tell Angular it's plain text
    }).subscribe({
      next: response => {
        alert(response); // Shows the success message from backend
        this.newAdmin = { name: '', email: '', password: '', role: '' };
      },
      error: error => {
        alert('Registration failed. You may not be authorized.');
        console.error(error);
      }
    });
    

  }

}

