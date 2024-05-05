import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourceListByTypeComponent } from './resource-list-by-type.component';

describe('ResourceListByTypeComponent', () => {
  let component: ResourceListByTypeComponent;
  let fixture: ComponentFixture<ResourceListByTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResourceListByTypeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResourceListByTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
