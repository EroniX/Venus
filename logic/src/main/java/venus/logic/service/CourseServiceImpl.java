package venus.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import venus.dal.model.Course;
import venus.dal.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public void save(Course course) {
        courseRepository.save(course);
    }

    public void delete(int id) {
        courseRepository.delete(id);
    }

    public Iterable<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(int id) {
        return courseRepository.findOne(id);
    }
}
