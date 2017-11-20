package venus.api;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import venus.dal.model.Course;
import venus.dal.model.Subject;
import venus.logic.service.CourseService;
import venus.logic.service.SubjectService;

@RestController
@RequestMapping("/api/course")
public class CourseApiController {
    /*@Autowired
    private SubjectService subjectService;
    @Autowired
    private CourseService courseService;

    @GetMapping
    @PreAuthorize("hasAuthority('COURSE_LIST')")
    public ResponseEntity<Iterable<Course>> list() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TRAINING_LIST')")
    public ResponseEntity<Iterable<Course>> list(@PathVariable int id) {
        Subject subject = subjectService.findById(id);
        if(subject == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(subject.getCourses());
    }
*/
    /*@DeleteMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('COURSE_STUDENT_REMOVE')")
    public ResponseEntity remove(@RequestBody int id) {
        User user = securityService.getUser();
        user.removeUserCourse(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add/{id}")
    @PreAuthorize("hasAuthority('COURSE_STUDENT_ADD')")
    public ResponseEntity add(@PathVariable int id) {
        Course course = courseService.findById(id);
        if(course == null) {
            return ResponseEntity.badRequest().build();
        }

        User user = securityService.getUser();
        UserCourse userCourse = UserCourse.make(user, course);
        user.addUserCourse(userCourse);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('COURSE_TEACHER_DELETE')")
    public ResponseEntity delete(@RequestBody int id) {
        User user = securityService.getUser();
        if(!user.hasTeachedCourse(id)) {
            return ResponseEntity.badRequest().build();
        }
        courseService.delete(id);
        // @TODO: Probably need to delete the UserCourses as well
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create/")
    @PreAuthorize("hasAuthority('COURSE_TEACHER_CREATE')")
    public ResponseEntity create(@RequestBody Course course) {
        courseService.save(course);
        return ResponseEntity.ok().build();
    }*/
//}
