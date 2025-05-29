import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageTestimonialComponent } from './page-testimonial.component';

describe('PageTestimonialComponent', () => {
  let component: PageTestimonialComponent;
  let fixture: ComponentFixture<PageTestimonialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PageTestimonialComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PageTestimonialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
