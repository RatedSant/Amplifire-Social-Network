import { TestBed } from '@angular/core/testing';

import { BroadcastObjectService } from './broadcast-object.service';

describe('BroadcastObjectService', () => {
  let service: BroadcastObjectService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BroadcastObjectService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
