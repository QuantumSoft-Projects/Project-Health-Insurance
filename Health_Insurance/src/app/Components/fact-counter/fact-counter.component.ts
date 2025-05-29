import { Component } from '@angular/core';
import { CounterDirective } from './counter.directive';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-fact-counter',
  standalone: true,
  imports: [CounterDirective,CommonModule],
  templateUrl: './fact-counter.component.html',
  styleUrl: './fact-counter.component.css'
})
export class FactCounterComponent {

}
