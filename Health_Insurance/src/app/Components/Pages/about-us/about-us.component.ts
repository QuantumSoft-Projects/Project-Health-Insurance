import { Component } from '@angular/core';
import { CounterDirective } from '../../fact-counter/counter.directive';

@Component({
  selector: 'app-about-us',
  standalone: true,
  imports: [CounterDirective],
  templateUrl: './about-us.component.html',
  styleUrl: './about-us.component.css'
})
export class AboutUsComponent {

}
