import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-car-insurance',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './car-insurance.component.html',
  styleUrl: './car-insurance.component.css'
})
export class CarInsuranceComponent {
   title = '"Drive Stress-Free with Full Coverage"';
  subtitle = 'Protect your vehicle and finances with our reliable and affordable car insurance plans.';
 
  benefits = [
    {
      title: '✔ Accident Protection',
      description: 'Coverage for vehicle damage or bodily injury due to accidents.',
      icon: 'car-crash'
    },
    {
      title: '✔ Third-Party Cover',
      description: 'Covers liability for damage to other people’s vehicles or property.',
      icon: 'users'
    },
    {
      title: '✔ Theft Protection',
      description: 'Insurance against vehicle theft or attempted theft.',
      icon: 'lock'
    },
    {
      title: '✔ Cashless Garage',
      description: 'Access to 3000+ network garages for seamless repairs.',
      icon: 'wrench'
    },
    {
      title: '✔ No Claim Bonus',
      description: 'Get discounts on premium for every claim-free year.',
      icon: 'gift'
    },
    {
      title: '✔ Roadside Assistance',
      description: '24/7 support for breakdowns, flat tires, towing and more.',
      icon: 'life-ring'
    },
   
  ];
 
  faqs = [
    {
      question: 'Can I transfer my no-claim bonus?',
      answer: 'Yes, your NCB can be transferred to your new insurer or vehicle.'
    },
    {
      question: 'Is car insurance mandatory?',
      answer: 'Yes, at least third-party insurance is legally required in India.'
    },
    {
      question: 'What is zero depreciation cover?',
      answer: 'It allows you to get full claim settlement without factoring depreciation on parts.'
    },
    {
      question: 'Do you offer long-term policies?',
      answer: 'Yes, we provide long-term policies up to 3 years for added convenience.'
    },
    {
      question: 'How fast is the claim settlement?',
      answer: 'Most claims are processed within 7 working days depending on the documentation.'
    }
  ];
 
  selectedFaq: number | null = null;
 
  toggleFaq(index: number): void {
    this.selectedFaq = this.selectedFaq === index ? null : index;
  }
 
  getQuote(): void {
    alert('We’ll contact you with a personalized quote soon.');
  }
 
  comparePlans(): void {
    alert('Redirecting to plan comparison page...');
  }
 
  connectSupport(): void {
    alert('Connecting you with our car insurance advisor...');
  }
 
  buyNow(): void {
    alert('Redirecting to secure purchase gateway...');
  }
}
