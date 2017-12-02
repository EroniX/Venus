import { TestBed, inject } from '@angular/core/testing';

import { UserCourseService } from './user-course.service';

describe('UserCourseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserCourseService]
    });
  });

  it('should be created', inject([UserCourseService], (service: UserCourseService) => {
    expect(service).toBeTruthy();
  }));
});
