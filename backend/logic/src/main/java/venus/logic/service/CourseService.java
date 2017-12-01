package venus.logic.service;

import venus.dal.model.Course;
import venus.dal.model.User;
import venus.logic.dto.CourseDTO;
import venus.logic.dto.UserCourseDTO;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    void save(CourseDTO courseDTO, User teacher);
    void delete(int id);
    Iterable<Course> findAll();
    Optional<Course> findById(int id);
    List<CourseDTO> convertToDTOs(List<Course> courses, User user);
    List<UserCourseDTO> convertToDTOs(Course course);
}
