package venus.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import venus.dal.model.Course;
import venus.dal.model.User;
import venus.dal.repository.CourseRepository;
import venus.logic.dto.CourseDTO;
import venus.logic.dto.UserCourseDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired //@TODO: Not a nice solution, will be fixed later
    private SubjectService subjectService;
    @Autowired
    private SemesterService semesterService;

    public void save(CourseDTO courseDTO, User teacher) {
        Course course = new Course();

        //@TODO: Into course
        course.setTeacher(teacher);
        course.setCapacity(courseDTO.getCapacity());
        course.setSubject(subjectService.findById(courseDTO.getSubjectId()).get());
        course.setSemester(semesterService.current().get());
        course.setStudentCourses(Collections.emptyList());

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
