import { TestBed } from '@angular/core/testing';

import { AdminEventListService } from './admin-event-list.service';

describe('AdminEventListService', () => {
  let service: AdminEventListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminEventListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
