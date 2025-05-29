import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-contact-us',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './contact-us.component.html',
  styleUrl: './contact-us.component.css'
})
export class ContactUsComponent {
  carouselItems =
  [
    {
      image: 'assets/img/slide-6.jpg',
      title: 'Quick & Easy Claims',
      description: 'Explore our reliable insurance plans with 24x7 support.',
      buttonText: 'Get Started',
      active: true
    },
    {
      image: 'assets/img/slide-7.jpg',
      title: 'Affordable Plans for Everyone',
      description: 'Choose the coverage that fits your budget and needs.',
      buttonText: ''
    },
    {
      image: 'assets/img/slide-5.jpg',
      title: 'Quick & Easy Claims',
      description: 'Experience hassle-free support when you need it the most.',
      buttonText: ''
    }
  ];
 
  insurancePlans = [
    {
      icon: 'fa-heartbeat',
      title: 'Family Health Shield',
      description: 'Comprehensive coverage for your entire family with cashless hospitalization.',
      features: [
        'Coverage up to ₹10L',
        'Pre-existing after 2 years',
        '2000+ network hospitals'
      ],
      buttonText: 'View Details'
    },
    {
      icon: 'fa-user-md',
      title: 'Senior Citizen Care',
      description: 'Specialized coverage for citizens above 60 years with pre-existing conditions.',
      features: [
        'Coverage up to ₹5L',
        'No upper age limit',
        'Free health checkups'
      ],
      buttonText: 'View Details'
    },
    {
      icon: 'fa-baby',
      title: 'Maternity Cover',
      description: 'Complete protection for expecting mothers and newborn babies.',
      features: [
        'Prenatal to postnatal care',
        'Newborn cover for 90 days',
        'Vaccination coverage'
      ],
      buttonText: 'View Details'
    }
  ];
 
  faqs = [
    {
      question: 'How do I file a claim?',
      answer: 'You can file claims through our mobile app, website portal, or by contacting our 24/7 helpline. Cashless claims are processed directly with network hospitals.'
    },
    {
      question: 'What documents are needed for claims?',
      answer: 'Typically you need the claim form, original bills, medical reports, prescription copies, and your policy document. The exact requirements vary by claim type.'
    },
    {
      question: 'Can I port my existing policy?',
      answer: 'Yes, we accept policy porting from other insurers without losing your continuity benefits, subject to terms and conditions.'
    }
  ];
 
  testimonials = [
    {
      rating: 5,
      text: '"The claim process was incredibly smooth when I needed emergency hospitalization. The support team guided me at every step."',
      name: 'Rahul Sharma',
      location: 'Mumbai',
      image: 'assets/img/testimonial-1.jpg'
    },
    {
      rating: 4,
      text: '"Affordable premiums with good coverage. Their network hospitals are conveniently located across the city."',
      name: 'Priya Patel',
      location: 'Bangalore',
      image: 'assets/img/testimonial-2.jpg'
    }
  ];
 
  resources = [
    {
      icon: 'fa-file-pdf',
      title: 'Policy Brochure',
      description: 'Detailed information about coverage, benefits and exclusions.',
      buttonText: 'Download (PDF)'
    },
    {
      icon: 'fa-file-contract',
      title: 'Claim Form',
      description: 'Standard claim form for reimbursement claims.',
      buttonText: 'Download (PDF)'
    },
    {
      icon: 'fa-list-check',
      title: 'Coverage Checklist',
      description: 'Checklist to understand what\'s covered under your plan.',
      buttonText: 'Download (PDF)'
    }
  ];
 
  contactInfo = [
    {
      icon: 'fa-map-marker-alt',
      title: 'Office Address',
      text: 'HealthPlus HQ, ABC Place, New Mumbai, India'
    },
    {
      icon: 'fa-envelope',
      title: 'Email Us',
      text: 'support@healthplus.in'
    },
    {
      icon: 'fa-phone-alt',
      title: 'Customer Care',
      text: '1800-123-4567'
    },
    {
      icon: 'fa-globe',
      title: 'Website',
      text: 'www.healthplus.in'
    }
  ];
 
  showChat = false;
 
  toggleChat() {
    this.showChat = !this.showChat;
  }
}
