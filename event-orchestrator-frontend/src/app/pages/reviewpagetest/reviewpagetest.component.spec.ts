import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewpagetestComponent } from './reviewpagetest.component';

describe('ReviewpagetestComponent', () => {
  let component: ReviewpagetestComponent;
  let fixture: ComponentFixture<ReviewpagetestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewpagetestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReviewpagetestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
