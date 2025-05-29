import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-quote-modal',
  templateUrl: './quote-modal.component.html',
  styleUrls: ['./quote-modal.component.css']
})
export class QuoteModalComponent {
  @Output() close = new EventEmitter<void>();

  quoteForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.quoteForm = this.fb.group({
      name: ['', Validators.required],
      mobile: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      pincode: ['', [Validators.required, Validators.pattern('^[0-9]{6}$')]]
    });
  }

  onSubmit(): void {
    if (this.quoteForm.valid) {
      console.log('Form Submitted:', this.quoteForm.value);
      // You can call your API here
    } else {
      this.quoteForm.markAllAsTouched(); // Show validation messages
    }
  }

  closeModal(): void {
    this.close.emit();
  }
}
