import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FactCounterComponent } from './fact-counter.component';

describe('FactCounterComponent', () => {
  let component: FactCounterComponent;
  let fixture: ComponentFixture<FactCounterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FactCounterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FactCounterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
