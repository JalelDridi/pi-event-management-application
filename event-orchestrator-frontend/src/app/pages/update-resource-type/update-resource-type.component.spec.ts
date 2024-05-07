import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateResourceTypeComponent } from './update-resource-type.component';

describe('UpdateResourceTypeComponent', () => {
  let component: UpdateResourceTypeComponent;
  let fixture: ComponentFixture<UpdateResourceTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateResourceTypeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateResourceTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
