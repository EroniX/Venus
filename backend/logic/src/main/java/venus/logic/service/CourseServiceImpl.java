package venus.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import venus.dal.model.Course;
import venus.dal.model.User;
import venus.dal.repository.CourseRepository;
import venus.logic.dto.CourseDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<Course> findById(int id) {
        return Optional.of(courseRepository.findOne(id));
    }

    @Override
    public List<CourseDTO> convertToDTOs(List<Course> courses, User user) {
        return courses
                .stream()
                .map(n -> CourseDTO.create(n, user))
                .collect(Collectors.toList());
    }
}
