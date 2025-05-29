import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PagefeatureComponent } from './pagefeature.component';

describe('PagefeatureComponent', () => {
  let component: PagefeatureComponent;
  let fixture: ComponentFixture<PagefeatureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PagefeatureComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PagefeatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
