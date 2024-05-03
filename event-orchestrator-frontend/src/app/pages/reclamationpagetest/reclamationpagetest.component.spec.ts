import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReclamationpagetestComponent } from './reclamationpagetest.component';

describe('ReclamationpagetestComponent', () => {
  let component: ReclamationpagetestComponent;
  let fixture: ComponentFixture<ReclamationpagetestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReclamationpagetestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReclamationpagetestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
