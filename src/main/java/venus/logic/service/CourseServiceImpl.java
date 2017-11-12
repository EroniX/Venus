package venus.logic.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import venus.dal.model.Course;
import venus.dal.repository.CourseRepository;

@Service
@SessionScope
@Data
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
