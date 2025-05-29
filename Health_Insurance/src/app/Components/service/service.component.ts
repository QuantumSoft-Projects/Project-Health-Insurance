import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
 
@Component({
  selector: 'app-service',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './service.component.html',
  styleUrls: ['./service.component.css']
})
export class ServiceComponent {
 
  activeSection: 'coverage' | 'benefits' = 'coverage'; // Show coverage section by default
 
  toggleSection(section: 'coverage' | 'benefits') {
    this.activeSection = section;
  }
 
  coverageServices = [
    {
      title: 'Individual Health Plans',
      description: 'Customized policies for individuals covering doctor visits, diagnostics, hospitalization, and post-hospital care.',
      icon: 'https://cdn-icons-png.flaticon.com/512/2926/2926390.png'
    },
    {
      title: 'Family Floater Plans',
      description: 'Flexible health coverage for your entire family under a single sum insured.',
      icon: 'https://cdn-icons-png.flaticon.com/512/2662/2662773.png'
    },
    {
      title: 'Critical Illness Cover',
      description: 'Lump sum payout on diagnosis of critical illnesses like cancer or heart attack.',
      icon: 'https://cdn-icons-png.flaticon.com/512/1077/1077010.png'
    },
    {
      title: 'Senior Citizen Plans',
      description: 'Plans for elders covering pre-existing conditions and annual health checkups.',
      icon: 'https://cdn-icons-png.flaticon.com/512/2354/2354075.png'
    },
    {
      title: 'Cashless Hospitalization',
      description: 'Get treatments at network hospitals without paying upfront.',
      icon: 'https://cdn-icons-png.flaticon.com/512/616/616690.png'
    },
    {
      title: 'Maternity & Newborn Cover',
      description: 'Covers delivery, pre/post-natal care, and newborn health from day one.',
      icon: 'https://cdn-icons-png.flaticon.com/512/2569/2569241.png'
    }
  ];
 
  primeBenefits = [
    {
      title: 'Financial Security',
      description: 'Protects your savings from large medical bills during emergencies.',
      icon: 'https://cdn-icons-png.flaticon.com/512/929/929571.png'
    },
    {
      title: 'Cashless Treatment',
      description: 'Easy access to a wide hospital network offering cashless care.',
      icon: 'https://cdn-icons-png.flaticon.com/512/929/929581.png'
    },
    {
      title: 'Tax Benefits',
      description: 'Premiums paid are tax-deductible under Section 80D.',
      icon: 'https://cdn-icons-png.flaticon.com/512/929/929556.png'
    },
    {
      title: 'Lifelong Renewability',
      description: 'Keep renewing your plan regardless of age.',
      icon: 'https://cdn-icons-png.flaticon.com/512/929/929572.png'
    },
    {
      title: 'Pre & Post Hospitalization',
      description: 'Covers related medical costs before and after hospitalization.',
      icon: 'https://cdn-icons-png.flaticon.com/512/929/929554.png'
    },
    {
      title: 'Easy Claims Process',
      description: 'Fast, hassle-free claim approvals and dedicated support.',
      icon: 'https://cdn-icons-png.flaticon.com/512/929/929567.png'
    }
  ];
}
   
 