package venus.logic.service;

import venus.dal.model.Course;

public interface CourseService {
    void save(Course course);
    void delete(int id);
    Iterable<Course> findAll();
    Course findById(int id);
}
