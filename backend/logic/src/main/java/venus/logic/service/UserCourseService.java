package venus.logic.service;

import venus.dal.model.Course;
import venus.dal.model.UserCourse;
import venus.logic.dto.UserCourseDTO;

import java.util.List;

public interface UserCourseService {
    void save(UserCourse userCourse);
    void delete(UserCourse userCourse);
    List<UserCourseDTO> convertToDTOs(Course course);
}
