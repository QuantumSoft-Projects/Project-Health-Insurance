import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageMembersComponent } from './page-members.component';

describe('PageMembersComponent', () => {
  let component: PageMembersComponent;
  let fixture: ComponentFixture<PageMembersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PageMembersComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PageMembersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
