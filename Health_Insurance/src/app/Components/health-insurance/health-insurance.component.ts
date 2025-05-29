import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-health-insurance',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './health-insurance.component.html',
  styleUrl: './health-insurance.component.css'
})
export class HealthInsuranceComponent implements OnInit {
  title = 'Smart Health Coverage, Built for You';

  subtitle = 'Discover health plans that combine affordability, flexibility, and all-round protection for your peace of mind.';

  // Features Data
  features = [
    { title: '✔ Cashless Hospitalization', description: 'Get treated at 10,000+ network hospitals without paying upfront.' },
    { title: '✔ Pre & Post Hospitalization', description: 'Coverage includes medical expenses before and after hospitalization.' },
    { title: '✔ Lifetime Renewability', description: 'Enjoy uninterrupted health protection for a lifetime.' },
    { title: '✔ Tax Benefits', description: 'Save up to ₹75,000 under section 80D of the Income Tax Act.' },
    { title: '✔ Daycare Procedures', description: 'Coverage for 500+ daycare treatments that don\'t require 24hr hospitalization.' },
    { title: '✔ Annual Health Checkup', description: 'Free annual preventive health checkups included in your plan.' },
    { title: '✔ Alternative Treatments', description: 'Coverage for Ayurveda, Homeopathy and Unani treatments.' },
    { title: '✔ Global Coverage', description: 'Worldwide protection including emergency overseas treatment.' }
  ];

  // Pricing Plans Data
  plans = [
    {
      name: 'Basic',
      price: '₹499/month',
      coverage: '5 Lakhs',
      features: [
        'Cashless hospitalization',
        'Pre-existing disease cover',
        'Annual health checkup'
      ]
    },
    {
      name: 'Family Floater',
      price: '₹1,299/month',
      coverage: '10 Lakhs',
      features: [
        'Covers 2 adults + 2 children',
        'Maternity benefits',
        'Newborn baby cover'
      ]
    }
  ];

  // Testimonials Data
  testimonials = [
    {
      name: 'Rahul Sharma',
      rating: 5,
      comment: 'Claim settlement was quick and hassle-free. Highly recommended!'
    },
    {
      name: 'Priya Patel',
      rating: 4,
      comment: 'Good customer support and wide network of hospitals.'
    }
  ];

  // FAQs Data
  faqs = [
    {
      question: 'What is the waiting period for pre-existing diseases?',
      answer: 'Typically 2-4 years depending on the insurer and plan chosen.'
    },
    {
      question: 'Can I port my existing health insurance?',
      answer: 'Yes, we facilitate seamless porting of your current policy.'
    }
  ];

  ngOnInit(): void {
    console.log('Component initialized');
  }

  // Button Actions
  getQuote(): void {
    alert('Redirecting to quote page...');
    // this.router.navigate(['/get-quote']);
  }

  selectPlan(planName: string): void {
    console.log(`Selected plan: ${planName}`);
    // Add your plan selection logic here
  }
}