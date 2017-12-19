package venus.api;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import venus.dal.model.Course;
import venus.dal.model.Subject;
import venus.dal.model.User;
import venus.logic.dto.CourseDTO;
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

    @GetMapping("/find-all")
    @PreAuthorize("hasAuthority('COURSE_FIND_ALL')")
    public ResponseEntity<Iterable<CourseDTO>> findAll() {
        User user = securityService.getUser();
        List<CourseDTO> courseDTOs = courseService.convertToDTOs(
                Lists.newArrayList(courseService.findAll()),
                user);

        return ResponseEntity.ok(courseDTOs);
    }

    @GetMapping("/find-by-id/{courseId}")
    @PreAuthorize("hasAuthority('COURSE_FIND')")
    public ResponseEntity<CourseDTO> findById(@PathVariable int courseId) {
        User user = securityService.getUser();
        Optional<Course> course = this.courseService.findById(courseId);
        if(!course.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        CourseDTO courseDTO = CourseDTO.create(
                course.get(),
                user);

        return ResponseEntity.ok(courseDTO);
    }

    @GetMapping("/find-by-subject-id/{subjectId}")
    @PreAuthorize("hasAuthority('COURSE_LIST')")
    public ResponseEntity<List<CourseDTO>> findBySubjectId(@PathVariable int subjectId) {
        User user = securityService.getUser();
        Optional<Subject> subject = subjectService.findById(subjectId);
        if(!subject.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        List<CourseDTO> courseDTOs = courseService.convertToDTOs(
                subject.get().getCourses(),
                user);

        return ResponseEntity.ok(courseDTOs);
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
