package venus.logic.service;

import venus.dal.model.UserCourse;

public interface UserCourseService {
    void save(UserCourse userCourse);
    void delete(UserCourse userCourse);
}
