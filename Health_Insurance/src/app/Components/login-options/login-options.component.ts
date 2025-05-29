import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SuperadminLoginComponent } from '../superadmin-login/superadmin-login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserLoginComponent } from '../user-login/user-login.component';
import { AdminLoginComponent } from '../admin-login/admin-login.component';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login-options',
  standalone: true,
  imports: [CommonModule,UserLoginComponent,AdminLoginComponent,SuperadminLoginComponent,FormsModule,ReactiveFormsModule,HttpClientModule],
  templateUrl: './login-options.component.html',
  styleUrl: './login-options.component.css'
})
export class LoginOptionsComponent {
  selectedRole: string = 'superadmin'; // default selected toggle

}
