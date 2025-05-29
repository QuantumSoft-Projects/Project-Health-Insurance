import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageAppointmentComponent } from './page-appointment.component';

describe('PageAppointmentComponent', () => {
  let component: PageAppointmentComponent;
  let fixture: ComponentFixture<PageAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PageAppointmentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PageAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
