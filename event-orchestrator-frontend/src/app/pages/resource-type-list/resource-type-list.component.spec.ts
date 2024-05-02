import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourceTypeListComponent } from './resource-type-list.component';

describe('ResourceTypeListComponent', () => {
  let component: ResourceTypeListComponent;
  let fixture: ComponentFixture<ResourceTypeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResourceTypeListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResourceTypeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
