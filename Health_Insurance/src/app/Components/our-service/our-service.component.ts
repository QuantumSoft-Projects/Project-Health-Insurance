import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-our-service',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './our-service.component.html',
  styleUrl: './our-service.component.css'
})
export class OurServiceComponent {
 insurances = [
    {
      image: 'assets/img/healthi.png',
      title: 'Health Insurance',
      description: 'Health insurance provides financial coverage for medical expenses, including doctor visits, hospital stays, surgeries, and medications.',
      route: '/healthInsurance'
    },
    {
      image: 'assets/img/car.jpg',
      title: 'Car Insurance',
      description: 'Car insurance offers protection against financial loss due to accidents, theft, or damage to your vehicle. It also provides liability.',
      route: '/carInsurance'
    },
    {
      image: 'assets/img/life.png',
      title: 'Life Insurance',
      description: 'Life insurance ensures your family’s financial security by providing lump sum payout to beneficiaries in case of your untimely demise.',
      route: '/lifeInsurance'
    },
    {
      image: 'assets/img/travel.png',
      title: 'Travel Insurance',
      description: 'Travel insurance covers unexpected events during trips such as trip cancellations, lost luggage, medical emergencies, or travel delays.',
      route: '/travelInsurance'
    },
    {
      image: 'assets/img/homei.png',
      title: 'Home Insurance',
      description: 'Home insurance protects your home and its contents from risks like fire, flood, burglary, and natural disasters. It helps in rebuilding.',
      route: '/homeInsurance'
    },
    {
      image: 'assets/img/termsi.png',
      title: 'Term Insurance',
      description: 'Term insurance is a pure life cover plan that offers high coverage at affordable premiums. It provides financial protection to your family.',
      route: '/termInsurance'
    }
  ];
}
