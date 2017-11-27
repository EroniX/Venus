package venus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import venus.dal.model.Course;
import venus.dal.model.Subject;
import venus.dal.model.User;
import venus.dal.model.UserCourse;
import venus.logic.dto.CourseDTO;
import venus.logic.service.*;

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
    private UserService userService;
    @Autowired
    private UserCourseService userCourseService;
    @Autowired
    private SecurityService securityService;

    @GetMapping
    @PreAuthorize("hasAuthority('COURSE_LIST_ALL')")
    public ResponseEntity<Iterable<Course>> listAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/list/{id}")
    //@PreAuthorize("hasAuthority('COURSE_LIST')")
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

    @PostMapping("/register")
    //@PreAuthorize("hasAuthority('COURSE_REGISTER')")
    public ResponseEntity<Boolean> register(@RequestBody int id) {
        Optional<Course> course = courseService.findById(id);
        if(!course.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        User user = securityService.getUser();
        //@TODO: Into van transaction
        //user.removeUserCourse(id)
        UserCourse userCourse = UserCourse.create(user, course.get());
        userCourseService.save(userCourse);
        //
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
    @PreAuthorize("hasAuthority('COURSE_TEACHER_DELETE')")
    public ResponseEntity delete(@RequestBody int id) {
        User user = securityService.getUser();
        if(!user.hasTeachedCourse(id)) {
            return ResponseEntity.badRequest().build();
        }
        courseService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create/")
    @PreAuthorize("hasAuthority('COURSE_TEACHER_CREATE')")
    public ResponseEntity create(@RequestBody Course course) {
        courseService.save(course);
        return ResponseEntity.ok().build();
    }
}
