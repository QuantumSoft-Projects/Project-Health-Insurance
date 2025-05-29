import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavComponent } from './Components/nav/nav.component';
import { CarouselComponent } from './Components/carousel/carousel.component';
import { FeaturesComponent } from './Components/features/features.component';
import { AboutComponent } from './Components/about/about.component';
import { AppointmentComponent } from './Components/appointment/appointment.component';
import { FooterComponent } from './Components/footer/footer.component';
import { FactCounterComponent } from './Components/fact-counter/fact-counter.component';
import { ServiceComponent } from './Components/service/service.component';
import { TestimonialsComponent } from './Components/testimonials/testimonials.component';
import { TeamComponent } from './Components/team/team.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,NavComponent,CarouselComponent,FeaturesComponent,AboutComponent,AppointmentComponent,FooterComponent,FactCounterComponent,AboutComponent,ServiceComponent,TestimonialsComponent,TeamComponent,CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'health-insurance';
  
}
