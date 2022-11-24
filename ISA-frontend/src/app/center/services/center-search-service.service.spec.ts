import { TestBed } from '@angular/core/testing';

import { CenterSearchServiceService } from './center-search-service.service';

describe('CenterSearchServiceService', () => {
  let service: CenterSearchServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CenterSearchServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
