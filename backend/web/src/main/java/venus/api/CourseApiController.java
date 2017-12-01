package venus.api;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import venus.dal.model.Course;
import venus.dal.model.Subject;
import venus.dal.model.User;
import venus.dal.model.UserCourse;
import venus.logic.dto.CourseDTO;
import venus.logic.dto.UserCourseDTO;
import venus.logic.service.CourseService;
import venus.logic.service.SecurityService;
import venus.logic.service.SubjectService;
import venus.logic.service.UserCourseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/course")
public class CourseApiController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserCourseService userCourseService;
    @Autowired
    private SecurityService securityService;

    @GetMapping
    @PreAuthorize("hasAuthority('COURSE_LIST_ALL')")
    public ResponseEntity<Iterable<CourseDTO>> listAll() {
        User user = securityService.getUser();
        List<CourseDTO> courseDTOs = courseService.convertToDTOs(
                Lists.newArrayList(courseService.findAll()),
                user);

        return ResponseEntity.ok(courseDTOs);
    }

    @GetMapping("/list/{id}")
    @PreAuthorize("hasAuthority('COURSE_LIST')")
    public ResponseEntity<List<CourseDTO>> list(@PathVariable int id) {
        User user = securityService.getUser();
        Optional<Subject> subject = subjectService.findById(id);
        if(!subject.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        List<CourseDTO> courseDTOs = courseService.convertToDTOs(
                subject.get().getCourses(),
                user);

        return ResponseEntity.ok(courseDTOs);
    }

    @GetMapping("/list-students/{id}")
    @PreAuthorize("hasAuthority('COURSE_STUDENTS_LIST')")
    public ResponseEntity<List<UserCourseDTO>> listStudents(@PathVariable int id) {
        Optional<Course> course = courseService.findById(id);
        if(!course.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        User user = securityService.getUser();
        List<UserCourseDTO> courseDTOs = courseService.convertToDTOs(course.get());

        return ResponseEntity.ok(courseDTOs);
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('COURSE_REGISTER')")
    public ResponseEntity<Boolean> register(@RequestBody int id) {
        Optional<Course> course = courseService.findById(id);
        if(!course.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        User user = securityService.getUser();
        UserCourse userCourse = UserCourse.create(user, course.get());
        userCourseService.save(userCourse);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/unregister")
    @PreAuthorize("hasAuthority('COURSE_UNREGISTER')")
    public ResponseEntity<Boolean> unregister(@RequestBody int id) {
        User user = securityService.getUser();
        Optional<Course> course = courseService.findById(id);
        if(!course.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<UserCourse> userCourse = course.get().getUserCourse(user.getId());
        if(!userCourse.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        userCourseService.delete(userCourse.get());
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('COURSE_DELETE')")
    public ResponseEntity delete(@RequestBody int id) {
        User user = securityService.getUser();
        if(!user.hasTeachedCourse(id)) {
            return ResponseEntity.badRequest().build();
        }
        courseService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('COURSE_CREATE')")
    public ResponseEntity<Boolean> create(@RequestBody CourseDTO courseDTO) {
        User user = securityService.getUser();
        courseService.save(courseDTO, user);
        return ResponseEntity.ok(true);
    }
}
