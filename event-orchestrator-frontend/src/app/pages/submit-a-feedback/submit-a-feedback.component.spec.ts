import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitAFeedbackComponent } from './submit-a-feedback.component';

describe('SubmitAFeedbackComponent', () => {
  let component: SubmitAFeedbackComponent;
  let fixture: ComponentFixture<SubmitAFeedbackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubmitAFeedbackComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubmitAFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
