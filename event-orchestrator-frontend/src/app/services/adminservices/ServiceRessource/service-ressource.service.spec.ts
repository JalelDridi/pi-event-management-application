import { TestBed } from '@angular/core/testing';

import { ServiceRessourceService } from './service-ressource.service';

describe('ServiceRessourceService', () => {
  let service: ServiceRessourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceRessourceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
