import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminRegistrationComponent } from '../admin-registration/admin-registration.component';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { DoctorComponent } from '../doctor/doctor.component';


type Section = 'dashboard'|'adminAccounts' | 'policies' | 'claims' | 'categories' | 'hospitals' | 'transactions' | 'requests' | 'reports' | 'settings'|'rider'|'customizedPolicies'|'doctors';

interface Hospital {
  hospitalId?: number;
  name: string;
  address: string;
  city: string;
  state: string;
  postalCode: string;
  contactNumber: string;
  email: string;
  website: string;
  isNetworkHospital: boolean;
  icuAvailable: boolean;
  availableBeds: number;
  isActive: boolean;
  lastUpdated?: string;
  specialties: string[];
  doctors?: any;
  active?: boolean;
  networkHospital?: boolean;
}

@Component({
  selector: 'app-superadmin-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule,AdminRegistrationComponent,ReactiveFormsModule,HttpClientModule,DoctorComponent],
  templateUrl: './superadmin-dashboard.component.html',
  styleUrl: './superadmin-dashboard.component.css'
})
export class SuperadminDashboardComponent implements OnInit {
  // ===== Dashboard Stats =====
  totalUsers = 0;
  totalPolicies = 0;
  totalClaims = 0;
  totalHospitals = 0;
  totalReports = 0;
  totalPayments = 0;

  // ===== Section Control =====
  activeSection: Section = 'dashboard';

  // ===== Policy Management =====
  newPolicy: any = {
    policyName: '',
    policyType: 'HEALTH',
    description: '',
    coverageAmount: '',
    premium: '',
    premiumPlan: 'ANNUALLY',
    termDuration: '1'
  };
  policies: any[] = [];
  selectedPolicy: any = null;
  token: string = 'Bearer ' + localStorage.getItem('token');
  constructor(private fb: FormBuilder,private http: HttpClient) {
  this.customForm = this.fb.group({
    premiumPlan: ['MONTHLY']
  });

  this.doctorForm = this.fb.group({
    name: [''],
    specialization: [''],
    email: [''],
    phone: [''],
    available: [false]
  });
}



  //===Customized Policy====

  customForm: FormGroup;
  customizedPolicyId: number | null = null;

  policyId = 2; // Example fixed policyId
  userId = 1;   // Example fixed userId

  riderId: number = 0;
  addOnId: number = 0;


  //===Doctor===
  doctorForm: FormGroup;
  doctors: any[] = [];

  isUpdating = false;
  selectedDoctorId: number | null = null;

  hospitalId: number = 0;
  searchHospitalId: number = 0;
  fetchDoctorId: number = 0;
  specialization: string = '';

onSubmitDoctor() {
  const doctorData = this.doctorForm.value;

  if (!this.hospitalId || this.hospitalId === 0) {
    alert('Please select a valid hospital.');
    return;
  }

  const token = localStorage.getItem('token');
  if (!token) {
    alert('User not authorized. Please log in again.');
    return;
  }

  const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

  if (this.isUpdating && this.selectedDoctorId != null) {
    // Update doctor
    this.http.put(`http://localhost:8080/api/doctors/${this.selectedDoctorId}`, doctorData, { headers }).subscribe({
      next: () => {
        alert('Doctor updated successfully!');
        this.fetchAll();
        this.resetForm();
      },
      error: err => alert('Error updating doctor: ' + err.message)
    });
  } else {
    // Add new doctor
    this.http.post(`http://localhost:8080/api/hospitals/${this.hospitalId}/doctors`, doctorData, { headers }).subscribe({
      next: () => {
        alert('Doctor added successfully!');
        this.fetchAll();
        this.resetForm();
      },
      error: err => alert('Error adding doctor: ' + err.message)
    });
  }
}

  

  fetchAll() {
    this.http.get<any[]>('http://localhost:8080/api/doctors').subscribe(data => this.doctors = data);
  }

  fetchAvailable() {
    this.http.get<any[]>('http://localhost:8080/api/doctors/available').subscribe(data => this.doctors = data);
  }

  fetchByHospital() {
    this.http.get<any[]>(`http://localhost:8080/api/hospitals/${this.searchHospitalId}/doctors`).subscribe(data => this.doctors = data);
  }

  fetchBySpecialization() {
    this.http.get<any[]>(`http://localhost:8080/api/doctors/search?specialization=${this.specialization}`).subscribe(data => this.doctors = data);
  }

  fetchById() {
    this.http.get<any>(`http://localhost:8080/api/doctors/${this.fetchDoctorId}`).subscribe(data => this.doctors = [data]);
  }

  editDoctor(doc: any) {
    this.isUpdating = true;
    this.selectedDoctorId = doc.doctorId;
    this.doctorForm.patchValue(doc);
  }

  resetForm() {
    this.doctorForm.reset();
    this.isUpdating = false;
    this.selectedDoctorId = null;
  }



submitCustomizedPolicy() {
  const url = `http://localhost:8080/api/customizedPolicy/customize/${this.policyId}/${this.userId}`;
  this.http.post<any>(url, this.customForm.value).subscribe({
    next: (res) => {
      this.customizedPolicyId = res.id;
      alert('Customized policy created successfully!');
    },
    error: (err) => {
      console.error(err);
      alert('Failed to customize policy.');
    }
  });
}

addRider() {
  if (!this.customizedPolicyId || !this.riderId) return;
  const url = `http://localhost:8080/api/customizedPolicy/addRider/${this.customizedPolicyId}/${this.riderId}`;
  this.http.patch(url, {}).subscribe({
    next: () => alert('Rider added successfully!'),
    error: (err) => {
      console.error(err);
      alert('Failed to add rider.');
    }
  });
}

addAddOn() {
  if (!this.customizedPolicyId || !this.addOnId) return;
  const url = `http://localhost:8080/api/customizedPolicy/addAddOn/${this.customizedPolicyId}/${this.addOnId}`;
  this.http.patch(url, {}).subscribe({
    next: () => alert('Add-On added successfully!'),
    error: (err) => {
      console.error(err);
      alert('Failed to add add-on.');
    }
  });
}

markAsExpired() {
  if (!this.customizedPolicyId) return;
  const url = `http://localhost:8080/api/premium/changeStatus/${this.customizedPolicyId}`;
  this.http.patch(url, {}).subscribe({
    next: () => alert('Policy marked as expired!'),
    error: (err) => {
      console.error(err);
      alert('Failed to change status.');
    }
  });
}

  // ===== Policy Methods =====
  addPolicy() {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    this.http.post('http://localhost:8080/api/policy/add', this.newPolicy, { headers })
      .subscribe(
        res => {
          console.log('Policy added:', res);
          alert('✅ Policy added successfully!');
          this.newPolicy = {
            policyName: '',
            policyType: 'HEALTH',
            description: '',
            coverageAmount: '',
            premium: '',
            premiumPlan: 'ANNUALLY',
            termDuration: '1'
          };
          this.loadPolicies(); // 🔁 Refresh list
        },
        err => {
          console.error('Error adding policy:', err);

          if (err.status === 403) {
            alert('❌ Access denied: You are not authorized to perform this action (403)');
          } else if (err.status === 404) {
            alert('❌ Error: API endpoint not found (404)');
          } else {
            alert('❌ An unexpected error occurred while adding the policy');
          }
        }
      );
  }




  private isPolicyFormValid(): boolean {
    return this.newPolicy.policyName.trim() !== '' &&
           this.newPolicy.description.trim() !== '' &&
           this.newPolicy.coverageAmount > 0 &&
           this.newPolicy.premium > 0 &&
           this.newPolicy.termDuration > 0;
  }

  loadPolicies() {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    this.http.get<any[]>('http://localhost:8080/api/policy/all')
    .subscribe(res => {
      this.policies = res;
      console.log(res);
    });

  }


  resetPolicyForm() {
    this.newPolicy = {
      policyName: '',
      policyType: 'HEALTH',
      description: '',
      coverageAmount: '',
      premium: '',
      premiumPlan: 'ANNUALLY',
      termDuration: '1'
    };
  }

  deletePolicy(policyId: number): void {
    if (!confirm('Are you sure you want to delete this policy?')) {
      return;
    }

    const token = localStorage.getItem('token');
    if (!token) {
      alert('Authorization token is missing.');
      return;
    }

    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    this.http.delete(`http://localhost:8080/api/policy/${policyId}`, { headers })
      .subscribe({
        next: () => {
          alert('Policy deleted successfully.');
          this.loadPolicies();
        },
        error: (err) => {
          console.error('Error deleting policy:', err);
          if (err.status === 403) {
            alert('You do not have permission to perform this action.');
          } else {
            alert('Failed to delete policy.');
          }
        }
      });
  }

  // ===== Admin Management =====
  newAdmin = {
    name: '',
    email: '',
    password: '',
    phone: '',
    role: ''
  };
  adminList = [
    { id: 1, name: 'Ajay Verma', email: 'ajay@admin.com', phone: '9999999999', role: 'Admin' },
    { id: 2, name: 'Priya Rani', email: 'priya@admin.com', phone: '8888888888', role: 'SubAdmin' }
  ];

  createAdmin() {
    const newEntry = { ...this.newAdmin, id: Date.now() };
    this.adminList.push(newEntry);
    this.resetAdminForm();
  }

  editAdmin(admin: any) {
    this.newAdmin = { ...admin };
    this.deleteAdmin(admin.id);
  }

  deleteAdmin(id: number) {
    this.adminList = this.adminList.filter(admin => admin.id !== id);
  }

  resetAdminForm() {
    this.newAdmin = { name: '', email: '', password: '', phone: '', role: '' };
  }

  // ===== Claim Management =====
  claim = { user: '', policyName: '', amount: 0 };
  claimList: any[] = [];

  addClaim() {
    if (this.claim.user && this.claim.policyName && this.claim.amount > 0) {
      this.claimList.push({
        id: Date.now(),
        ...this.claim
      });
      this.claim = { user: '', policyName: '', amount: 0 };
    }
  }

  deleteClaim(id: number) {
    this.claimList = this.claimList.filter(claim => claim.id !== id);
  }

  // ===== Insurance Categories =====
  newCategory = { name: '', description: '' };
  categoryList = [
    { id: 1, name: 'PPO', description: 'Preferred Provider Organization' },
    { id: 2, name: 'HMO', description: 'Health Maintenance Organization' }
  ];

  addCategory() {
    if (this.newCategory.name.trim() && this.newCategory.description.trim()) {
      this.categoryList.push({
        id: Date.now(),
        name: this.newCategory.name,
        description: this.newCategory.description
      });
      this.newCategory = { name: '', description: '' };
    }
  }

  editCategory(category: any) {
    this.newCategory = { name: category.name, description: category.description };
    this.deleteCategory(category.id);
  }

  deleteCategory(id: number) {
    this.categoryList = this.categoryList.filter(cat => cat.id !== id);
  }

  // ===== Hospital & Provider Management =====
  hospital = { name: '', hospitalDescription: '', providerName: '', providerSpecialty: '' };
  hospitalList: any[] = [];



  // ===== Transaction Management =====
  transaction: any = {};
  transactionList: any[] = [];

  addTransaction() {
    if (this.transaction.transactionId && this.transaction.userName && this.transaction.amount) {
      this.transactionList.push({ ...this.transaction });
      this.transaction = {};
    }
  }

  editTransaction(transaction: any) {
    this.transaction = { ...transaction };
  }

  deleteTransaction(transactionId: string) {
    this.transactionList = this.transactionList.filter(t => t.transactionId !== transactionId);
  }

  // ===== User Requests Management =====
  userRequest = { userName: '', requestType: '', description: '', status: 'Pending' };
  userRequestList: any[] = [];

  addUserRequest() {
    if (this.userRequest.userName.trim() && this.userRequest.requestType.trim() && this.userRequest.description.trim()) {
      this.userRequestList.push({
        id: Date.now(),
        ...this.userRequest
      });
      this.resetUserRequestForm();
    }
  }

  editUserRequest(request: any) {
    this.userRequest = { ...request };
    this.deleteUserRequest(request.id);
  }

  deleteUserRequest(id: number) {
    this.userRequestList = this.userRequestList.filter(request => request.id !== id);
  }

  resetUserRequestForm() {
    this.userRequest = { userName: '', requestType: '', description: '', status: 'Pending' };
  }

  // ===== Report Management =====
  report = { type: '' };
  reportList: any[] = [];

  generateReport() {
    const newReport = { type: this.report.type, date: new Date().toLocaleDateString() };
    this.reportList.push(newReport);
    this.report.type = '';
  }


  editPolicy(policy: any): void {
    this.selectedPolicy = { ...policy }; // Create a copy for editing
    // Logic to open the edit modal goes here
  }

    // Update policy (called on form submit)
    updatePolicy(): void {
      const token = localStorage.getItem('token');
      if (!token) {
        console.log('Token is missing');
        alert('Authorization token is missing.');
        return;
      }
    
      console.log('Token being sent:', token); // Log token for inspection
      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
      
      this.http.put(
        `http://localhost:8080/api/policy/${this.selectedPolicy.policyId}`,
        this.selectedPolicy,
        { headers }
      ).subscribe({
        next: (response) => {
          console.log('Policy updated successfully:', response);
          alert('Policy updated successfully.');
          this.loadPolicies();
          this.selectedPolicy = null;
        },
        error: (err) => {
          console.error('Error updating policy:', err);
          console.error('Full error response:', err);
          if (err.status === 403) {
            alert('You do not have permission to perform this action.');
          } else {
            alert('Failed to update policy.');
          }
        }
      });
    }


     // ----rider -----
     riderData: { name: string; description: string; premiumPerYear: number | null } = {
      name: '',
      description: '',
      premiumPerYear: null
    };
    riderList: any[] = [];

    
    // addRider() {
    //   const token = localStorage.getItem('jwtToken');
    //   const headers = {
    //     headers: {
    //       Authorization: `Bearer ${token}`
    //     }
    //   };
    
    //   if (this.riderData.name && this.riderData.description && this.riderData.premiumPerYear != null) {
    //     this.http.post('http://localhost:8080/api/rider/add', this.riderData, headers).subscribe(
    //       response => {
    //         console.log('Rider added successfully', response);
    //         alert('Rider added successfully!');
    //         this.riderData = {
    //           name: '',
    //           description: '',
    //           premiumPerYear: null
    //         };
    //         this.fetchAllRiders(); // Refresh list
    //       },
    //       error => {
    //         console.error('Error adding rider:', error);
    //         alert('Failed to add rider.');
    //       }
    //     );
    //   }
    // }
    

    //fetch all riders
    fetchAllRiders() {
      const token = localStorage.getItem('jwtToken'); // Adjust key if needed
      const headers = {
        headers: {
          Authorization: `Bearer ${token}`
        }
      };
    
      this.http.get<any[]>('http://localhost:8080/api/rider/fetchAll', headers).subscribe(
        data => {
          this.riderList = data;
        },
        error => {
          console.error('Failed to fetch riders', error);
        }
      );
    }


    //hospital 

    hospitalData: Hospital = {
      name: '',
      address: '',
      city: '',
      state: '',
      postalCode: '',
      contactNumber: '',
      email: '',
      website: '',
      isNetworkHospital: false,
      icuAvailable: false,
      availableBeds: 0,
      isActive: true,
      specialties: []
    };
  
    // List of hospitals
    hospitals: Hospital[] = [];
  
    // Specialty input helper
    specialtyInput = '';
    
    // City filter
    filterCity = '';
  

    private getAuthHeaders(): HttpHeaders {
      const token = localStorage.getItem('token');
      return new HttpHeaders().set('Authorization', `Bearer ${token}`);
    }
  
    // Add a specialty tag
    addSpecialty(): void {
      const s = this.specialtyInput.trim();
      if (s && !this.hospitalData.specialties.includes(s)) {
        this.hospitalData.specialties.push(s);
      }
      this.specialtyInput = '';
    }
  
    // Remove a specialty tag
    removeSpecialty(s: string): void {
      this.hospitalData.specialties = this.hospitalData.specialties.filter(x => x !== s);
    }
  
    // Create hospital (SUPERADMIN only)
    addHospital(): void {
      const token = localStorage.getItem('token');
      if (!token) {
        alert('Not logged in');
        return;
      }
      const headers = this.getAuthHeaders();
  
      this.http.post<any>('http://localhost:8080/api/hospitals', this.hospitalData, { headers })
        .subscribe({
          next: () => {
            alert('Hospital added successfully');
            this.resetForm();
            this.fetchAllHospitals();
          },
          error: err => this.handleError(err)
        });
    }
  
    // Fetch all hospitals
    fetchAllHospitals(): void {
      const headers = this.getAuthHeaders();
      this.http.get<Hospital[]>('http://localhost:8080/api/hospitals', { headers })
        .subscribe({ next: data => this.hospitals = data, error: err => console.error(err) });
    }
  
    // Fetch by city filter
    fetchByCity(): void {
      if (!this.filterCity.trim()) {
        this.fetchAllHospitals();
        return;
      }
      const headers = this.getAuthHeaders();
      this.http.get<Hospital[]>(`http://localhost:8080/api/hospitals/city/${this.filterCity}`, { headers })
        .subscribe({ next: data => this.hospitals = data, error: err => console.error(err) });
    }
  
    // Fetch network hospitals
    fetchNetworkHospitals(): void {
      const headers = this.getAuthHeaders();
      this.http.get<Hospital[]>('http://localhost:8080/api/hospitals/network', { headers })
        .subscribe({ next: data => this.hospitals = data, error: err => console.error(err) });
    }
  
    // Fetch ICU-capable hospitals
    fetchICUHotels(): void {
      const headers = this.getAuthHeaders();
      this.http.get<Hospital[]>('http://localhost:8080/api/hospitals/icu', { headers })
        .subscribe({ next: data => this.hospitals = data, error: err => console.error(err) });
    }
  
    // Load hospital into form for editing
    editHospital(h: Hospital): void {
      this.hospitalData = { ...h };
    }
  
    // Update hospital
    updateHospital(): void {
      if (!this.hospitalData.hospitalId) return;
      const headers = this.getAuthHeaders();
      this.http.put<any>(
        `http://localhost:8080/api/hospitals/${this.hospitalData.hospitalId}`,
        this.hospitalData,
        { headers }
      ).subscribe({
        next: () => {
          alert('Hospital updated successfully');
          this.resetForm();
          this.fetchAllHospitals();
        },
        error: err => this.handleError(err)
      });
    }
  
    // Delete a hospital
    deleteHospital(id: number): void {
      if (!confirm('Confirm delete?')) return;
      const headers = this.getAuthHeaders();
      this.http.delete(`http://localhost:8080/api/hospitals/${id}`, { headers })
        .subscribe({ next: () => this.fetchAllHospitals(), error: err => this.handleError(err) });
    }
  
    // Reset the form
    // resetForm(): void {
    //   this.hospitalData = {
    //     name: '', address: '', city: '', state: '', postalCode: '',
    //     contactNumber: '', email: '', website: '',
    //     isNetworkHospital: false, icuAvailable: false,
    //     availableBeds: 0, isActive: true, specialties: []
    //   };
    //   this.filterCity = '';
    // }
  
    // Centralized error handler
    private handleError(err: any): void {
      console.error(err);
      if (err.status === 403) {
        alert('Access denied: SUPERADMIN only');
      } else {
        alert('Operation failed');
      }
    }
  
    
    

     // Cancel edit
  cancelEdit(): void {
    this.selectedPolicy = null;
  }

  downloadReport(reportId: string) {
    console.log(`Downloading report with ID: ${reportId}`);
  }

  // ===== Settings Management =====
  settings = { systemName: '', supportEmail: '', maintenanceMode: 'Off' };

  updateSettings() {
    console.log('Settings updated:', this.settings);
  }

  // ===== Section Handling =====
  setSection(section: Section) {
    this.activeSection = section;
  }

  // ===== Initialize Component =====
  ngOnInit(): void {
  this.loadPolicies();
    this.fetchAllRiders();
    this.loadInitialStats();
  }

  private loadInitialStats() {
    // You can replace these with actual API calls if needed
    this.totalUsers = 1200;
    this.totalClaims = 178;
    this.totalHospitals = 62;
    this.totalReports = 35;
    this.totalPayments = 150;
  }
}
