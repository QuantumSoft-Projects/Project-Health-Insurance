import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule,FormsModule,HttpClientModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent {
  // Track the current active section
  activeSection: string = 'dashboard'; // Default to 'dashboard' on load
  pendingAppointments: any[] = [];
  appointments: any[] = [];
  isLoading = false;
errorMessage = '';
 


  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadPendingAppointments();
    this.fetchAppointments();
  }
  // Data for each section (Health Insurance Data)
  dashboardData = {
    totalUsers: 1200,
    totalClaims: 450,
    paymentsReceived: '₹150000',
    reports: 35
  };
 
  usersData = {
    totalUsers: 1200,
    activeUsers: 1100,
    inactiveUsers: 100
  };
 
  claimsData = {
    totalClaims: 500,
    approvedClaims: 450,
    pendingClaims: 50
  };
 
  policiesData = {
    totalPolicies: 500,
    activePolicies: 450,
    expiredPolicies: 50
  };
 
  hospitalsData = {
    totalHospitals: 120,
    activeHospitals: 110,
    inactiveHospitals: 10
  };
 
  paymentsData = {
    totalPayments: 2000,
    completedPayments: 1900,
    pendingPayments: 100
  };
 
  settingsData = {
    siteName: 'Health Insurance Admin Panel',
    theme: 'Light',
    language: 'English'
  };

 
  // New Data for Adding Items (Users, Claims, Policies, Hospitals, Payments)
  newUser = {
    username: '',
    email: '',
    status: 'active'
  };
 
  newClaim = {
    claimName: '',
    status: 'approved'
  };
 
  newPolicy = {
    policyName: '',
    policyType: '',
    status: 'active'
  };
 
  newHospital = {
    hospitalName: '',
    location: '',
    status: 'active'
  };
 
  newPayment = {
    amount: '',
    status: 'completed'
  };

 
  // Method to handle adding a new user
  addUser() {
    console.log('User added:', this.newUser);
    // Handle the user addition logic here (e.g., send data to a backend)
  }
 
  // Method to handle adding a new claim
  addClaim() {
    console.log('Claim added:', this.newClaim);
    // Handle the claim addition logic here (e.g., send data to a backend)
  }
 
  // Method to handle adding a new policy
  addPolicy() {
    console.log('Policy added:', this.newPolicy);
    // Handle the policy addition logic here (e.g., send data to a backend)
  }


  //method to handle appointment 

  handleSidebarClick(section: string) {
    this.activeSection = section;
    if (section === 'appointments') {
      this.loadPendingAppointments();
    }
  }

  fetchAppointments(): void {
    this.isLoading = true;
    this.errorMessage = '';
  
    this.http.get<any[]>('http://localhost:8080/api/appointments/appointments').subscribe({
      next: (data) => {
        console.log('Appointments fetched:', data);
        this.appointments = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error fetching appointments:', err);
        this.errorMessage = 'Failed to load appointments';
        this.isLoading = false;
      }
    });
  }


  loadPendingAppointments() {
    this.http.get<any[]>('http://localhost:8080/api/appointments?status=pending')
      .subscribe(data => this.pendingAppointments = data);
  }

  confirmAppointment(appt: any): void {
    if (!appt || !appt.id) {
      console.error('Appointment ID is missing');
      return;
    }
  
    const token = localStorage.getItem('token'); // or sessionStorage, depending on your auth storage
  
    if (!token) {
      alert('You must be logged in to confirm appointments.');
      return;
    }
  
    const headers = {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    };
  
    const consultationLink = 'https://your-consultation-link.com'; // Replace with actual link logic if dynamic
  
    this.http.put(
      `http://localhost:8080/api/appointments/confirm/${appt.id}?consultationLink=${encodeURIComponent(consultationLink)}`,
      {},
      headers
    ).subscribe({
      next: () => {
        appt.status = 'CONFIRMED';
        alert('Appointment confirmed!');
      },
      error: err => {
        console.error('Error:', err);
        alert('Failed to confirm appointment');
      }
    });
  }
  
 
  // Method to handle adding a new hospital
  addHospital() {
    console.log('Hospital added:', this.newHospital);
    // Handle the hospital addition logic here (e.g., send data to a backend)
  }
 
  // Method to handle adding a new payment
  addPayment() {
    console.log('Payment added:', this.newPayment);
    // Handle the payment addition logic here (e.g., send data to a backend)
  }
 
  // Method to update settings
  updateSettings() {
    console.log('Settings updated:', this.settingsData);
    // Handle the settings update logic here (e.g., send data to a backend)
  }
}
