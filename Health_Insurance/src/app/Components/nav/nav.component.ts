import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QuoteModalComponent } from '../quote-modal/quote-modal.component';
import { Router } from '@angular/router';
import bootstrap from 'bootstrap';
 
@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [CommonModule, QuoteModalComponent],
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent {
  isQuoteModalOpen = false;
 
  constructor(
     
    private router: Router
   
  ) {}
  openQuoteModal(): void {
    this.isQuoteModalOpen = true;
  }
 
  closeQuoteModal(): void {
    this.isQuoteModalOpen = false;
  }
 
  navigateTo(route: string) {
    this.router.navigate([route]);
  }
  // This method will open the Signup modal when called
  openSignupModal() {
    const signupModalElement = document.getElementById('signupModal');
    if (signupModalElement) {
      const signupModal = new (window as any).bootstrap.Modal(signupModalElement);
      signupModal.show();
    }
  }
 
  goToLogin(): void {
    this.router.navigate(['/login-options']);  // change the route path if needed
  }
}
 
 