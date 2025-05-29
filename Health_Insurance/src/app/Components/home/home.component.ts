import { Component } from '@angular/core';
import { Carousel } from 'bootstrap';
import { CarouselComponent } from '../carousel/carousel.component';
import { AboutComponent } from '../about/about.component';
import { FactCounterComponent } from '../fact-counter/fact-counter.component';
import { FeaturesComponent } from '../features/features.component';
import { ServiceComponent } from '../service/service.component';
import { TeamComponent } from '../team/team.component';
import { TestimonialsComponent } from '../testimonials/testimonials.component';
import { AppointmentComponent } from '../appointment/appointment.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CarouselComponent,AboutComponent,FactCounterComponent,FeaturesComponent,ServiceComponent,TeamComponent,TestimonialsComponent,AppointmentComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
