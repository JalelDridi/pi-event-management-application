import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReclamationsStatisticsComponent } from './reclamations-statistics.component';

describe('ReclamationsStatisticsComponent', () => {
  let component: ReclamationsStatisticsComponent;
  let fixture: ComponentFixture<ReclamationsStatisticsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReclamationsStatisticsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReclamationsStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
