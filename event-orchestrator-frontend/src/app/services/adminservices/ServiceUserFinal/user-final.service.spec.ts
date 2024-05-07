import { TestBed } from '@angular/core/testing';

import { UserFinalService } from './user-final.service';

describe('UserFinalService', () => {
  let service: UserFinalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserFinalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
