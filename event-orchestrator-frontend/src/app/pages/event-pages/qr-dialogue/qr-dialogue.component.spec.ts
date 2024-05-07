import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QrDialogueComponent } from './qr-dialogue.component';

describe('QrDialogueComponent', () => {
  let component: QrDialogueComponent;
  let fixture: ComponentFixture<QrDialogueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QrDialogueComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QrDialogueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
