import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserListFinalComponent } from './user-list-final.component';

describe('UserListFinalComponent', () => {
  let component: UserListFinalComponent;
  let fixture: ComponentFixture<UserListFinalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserListFinalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserListFinalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
