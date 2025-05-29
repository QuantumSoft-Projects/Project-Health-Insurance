import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-quote-modal',
  standalone: true,
  templateUrl: './quote-modal.component.html',
  styleUrls: ['./quote-modal.component.css'],
  imports: [CommonModule, ReactiveFormsModule,FormsModule]
})
export class QuoteModalComponent {
   currentStep: number = 1;
  quoteSubmitted = false;
  selectedPlanId: number | null = null;
 
  // Form Data
  formData: any = {
    name: '',
    mobile: '',
    email: '',
    city: '',
    sumInsured: null,
    members: [{ relation: 'self', age: null, gender: '' }],
    addons: {
      maternity: false,
      opd: false,
      hospitalCash: false,
      criticalIllness: false
    },
    healthDetails: '',
    nominee: '',
    address: ''
  };
 
  // Insurance Plans
  insurancePlans = [
    { id: 1, title: 'Basic Health Plan', description: 'Covers up to ₹2 lakhs.', price: 3000 },
    { id: 2, title: 'Premium Health Plan', description: 'Covers up to ₹10 lakhs.', price: 9000 },
    { id: 3, title: 'Family Plan', description: 'Covers whole family up to ₹15 lakhs.', price: 12000 }
  ];
 
  // Payment Data
  paymentMethod: string = '';
  paymentMethodDisplay: string = '';
  transactionId: string = '';
  paymentDate: Date = new Date();
 
  cardDetails = {
    cardNumber: '',
    cardName: '',
    expiry: '',
    cvv: ''
  };
 
  upiDetails = {
    upiId: ''
  };
 
  netBankingDetails = {
    bank: ''
  };
 
  // Step Navigation
  goToStep(step: number) {
    this.currentStep = step;
  }
 
  // Add another member
  addMember() {
    this.formData.members.push({ relation: '', age: null, gender: '' });
  }
 
  // Select a plan
  selectPlan(planId: number) {
    this.selectedPlanId = planId;
  }
 
  // Get selected plan details
  getSelectedPlan() {
    return this.insurancePlans.find(plan => plan.id === this.selectedPlanId);
  }
 
  // Calculate total premium with GST and add-ons
  calculateTotalPremium(): number {
    const basePrice = this.getSelectedPlan()?.price || 0;
 
    let addonsTotal = 0;
    if (this.formData.addons) {
      if (this.formData.addons.maternity) addonsTotal += 1000;
      if (this.formData.addons.opd) addonsTotal += 800;
      if (this.formData.addons.hospitalCash) addonsTotal += 600;
      if (this.formData.addons.criticalIllness) addonsTotal += 1200;
    }
 
    const totalBeforeGST = basePrice + addonsTotal;
    const gst = totalBeforeGST * 0.18;
    return Math.round(totalBeforeGST + gst);
  }
 
  // Upload document
  uploadDocument(event: any) {
    const file = event.target.files[0];
    console.log('Uploaded file:', file?.name);
  }
 
  // Save quote data
  saveQuote() {
    console.log('Quote saved:', this.formData);
    alert('Quote saved! Check your email/SMS.');
    this.quoteSubmitted = true;
  }
 
  // Select payment method
  selectPaymentMethod(method: string) {
    this.paymentMethod = method;
  }
 
  // Submit payment and show confirmation
  submitPayment(method: string) {
    this.paymentMethod = method;
 
    // Validation
    if (method === 'card') {
      if (!this.cardDetails.cardNumber || !this.cardDetails.cardName || !this.cardDetails.expiry || !this.cardDetails.cvv) {
        alert('Please fill in all card details.');
        return;
      }
    } else if (method === 'upi') {
      if (!this.upiDetails.upiId) {
        alert('Please enter your UPI ID.');
        return;
      }
    } else if (method === 'netbanking') {
      if (!this.netBankingDetails.bank) {
        alert('Please select a bank.');
        return;
      }
    }
 
    // Set transaction info
    this.paymentMethodDisplay = this.getPaymentMethodName(method);
    this.transactionId = this.generateTransactionId();
    this.paymentDate = new Date();
 
    console.log('Payment successful by:', method);
    console.log('Transaction ID:', this.transactionId);
 
    // Show Thank You alert
    alert("✅ Thank you for purchasing health insurance with us! Your payment has been received and your policy is now being processed.");
 
    // Simulate delay and navigate to confirmation
    setTimeout(() => {
      this.goToStep(15); // Confirmation step
      this.resetForm();  // Optional: Reset form
    }, 1000);
  }
 
  // Get readable name of payment method
  getPaymentMethodName(method: string): string {
    switch (method) {
      case 'card': return 'Credit/Debit Card';
      case 'upi': return 'UPI';
      case 'netbanking': return 'Net Banking';
      default: return 'Unknown';
    }
  }
 
  // Generate mock transaction ID
  generateTransactionId(): string {
    const now = new Date();
    return 'TXN' + now.getTime().toString().slice(-8);
  }
 
  // Form submission: Step 1 -> Step 2
  onSubmitForm() {
    this.goToStep(2);
  }
 
  // Manual payment confirmation
  confirmPayment() {
    console.log('Payment confirmed');
    this.goToStep(15);
  }
 
  // Reset the entire form and data
  resetForm() {
    this.currentStep = 1;
    this.quoteSubmitted = false;
    this.selectedPlanId = null;
 
    this.paymentMethod = '';
    this.paymentMethodDisplay = '';
    this.transactionId = '';
    this.paymentDate = new Date();
 
    this.formData = {
      name: '',
      mobile: '',
      email: '',
      city: '',
      sumInsured: null,
      members: [{ relation: 'self', age: null, gender: '' }],
      addons: {
        maternity: false,
        opd: false,
        hospitalCash: false,
        criticalIllness: false
      },
      healthDetails: '',
      nominee: '',
      address: ''
    };
 
    this.cardDetails = {
      cardNumber: '',
      cardName: '',
      expiry: '',
      cvv: ''
    };
 
    this.upiDetails = {
      upiId: ''
    };
 
    this.netBankingDetails = {
      bank: ''
    };
  }
}
