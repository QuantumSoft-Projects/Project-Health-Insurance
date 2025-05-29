import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-life-insurance',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './life-insurance.component.html',
  styleUrl: './life-insurance.component.css'
})
export class LifeInsuranceComponent {
 title = 'Comprehensive Life Insurance Solutions';
  subtitle = 'Protect your family\'s future with our tailored insurance plans that offer security and growth opportunities.';

  benefits = [
    {
      title: '✔ Financial Security',
      description: 'Provides a lump sum amount to your family in case of an unfortunate event.',
      icon: 'shield'
    },
    {
      title: '✔ Tax Benefits',
      description: 'Premiums paid are eligible for tax deduction under Section 80C and maturity benefits are tax-free under Section 10(10D).',
      icon: 'tax'
    },
    {
      title: '✔ Loan Facility',
      description: 'Borrow up to 90% of your policy value in times of financial need.',
      icon: 'loan'
    },
    {
      title: '✔ Wealth Creation',
      description: 'Participate in market-linked returns while maintaining insurance coverage.',
      icon: 'wealth'
    },
    {
      title: '✔ Critical Illness Cover',
      description: 'Additional protection against 30+ critical illnesses with early payout benefit.',
      icon: 'health'
    },
    {
      title: '✔ Flexible Premiums',
      description: 'Choose payment terms that suit your financial situation - monthly, quarterly, or annually.',
      icon: 'flexible'
    }
  ];

  planTypes = [
    {
      name: 'Term Insurance',
      features: ['Pure protection', 'Low premium', 'High coverage', 'No maturity benefit'],
      bestFor: 'Young individuals seeking maximum coverage at minimum cost'
    },
    {
      name: 'Whole Life',
      features: ['Lifetime coverage', 'Cash value accumulation', 'Premium payment for limited period'],
      bestFor: 'Those wanting coverage for entire lifetime with savings component'
    },
    {
      name: 'Endowment',
      features: ['Savings + protection', 'Guaranteed returns', 'Maturity benefit', 'Loan facility'],
      bestFor: 'Conservative investors wanting guaranteed returns with insurance'
    },
    {
      name: 'ULIP',
      features: ['Market-linked returns', 'Flexible fund options', 'Partial withdrawals', 'Tax benefits'],
      bestFor: 'Those comfortable with market risks for higher potential returns'
    }
  ];

  testimonials = [
    {
      name: 'Rahul Sharma',
      role: 'IT Professional',
      quote: 'The claim settlement was hassle-free and quick when my family needed it the most.'
    },
    {
      name: 'Priya Patel',
      role: 'Homemaker',
      quote: 'The additional critical illness rider helped us during my husband\'s medical treatment.'
    }
  ];

  faqs = [
    {
      question: 'What is the ideal coverage amount?',
      answer: 'Typically 10-15 times your annual income, but we can help calculate your exact needs.'
    },
    {
      question: 'Can I change my nominee later?',
      answer: 'Yes, you can update your nominee anytime during the policy term.'
    }
  ];

  selectedFaq: number | null = null;  // This will keep track of the selected FAQ

  toggleFaq(index: number): void {
    // If the clicked FAQ is already open, close it. Otherwise, open it and close others.
    if (this.selectedFaq === index) {
      this.selectedFaq = null;
    } else {
      this.selectedFaq = index;
    }
  }

  getQuote(): void {
    alert('Our advisor will contact you shortly to discuss the best plan for your needs.');
  }

  viewPlans(): void {
    alert('Redirecting to detailed plans comparison page...');
  }

  contactAdvisor(): void {
    alert('Connecting you with a certified insurance advisor...');
  }
}
