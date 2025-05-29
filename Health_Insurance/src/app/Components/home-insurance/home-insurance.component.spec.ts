import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeInsuranceComponent } from './home-insurance.component';

describe('HomeInsuranceComponent', () => {
  let component: HomeInsuranceComponent;
  let fixture: ComponentFixture<HomeInsuranceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomeInsuranceComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HomeInsuranceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
