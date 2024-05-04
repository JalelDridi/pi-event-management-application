import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateEventRequestComponent } from './create-event-request.component';

describe('CreateEventRequestComponent', () => {
  let component: CreateEventRequestComponent;
  let fixture: ComponentFixture<CreateEventRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateEventRequestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateEventRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
