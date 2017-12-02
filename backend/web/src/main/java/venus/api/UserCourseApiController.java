package venus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import venus.dal.model.Course;
import venus.dal.model.User;
import venus.dal.model.UserCourse;
import venus.logic.dto.UserCourseDTO;
import venus.logic.dto.UserCourseMarkDTO;
import venus.logic.service.CourseService;
import venus.logic.service.SecurityService;
import venus.logic.service.UserCourseService;
import venus.logic.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-course")
public class UserCourseApiController {
    @Autowired
    private UserCourseService userCourseService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/find-by-course-id/{courseId}")
    @PreAuthorize("hasAuthority('USER_COURSE_LIST')")
    public ResponseEntity<List<UserCourseDTO>> findByCourseId(@PathVariable int courseId) {
        Optional<Course> course = courseService.findById(courseId);
        if(!course.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        List<UserCourseDTO> courseDTOs = userCourseService.convertToDTOs(course.get());
        return ResponseEntity.ok(courseDTOs);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER_COURSE_CREATE')")
    public ResponseEntity<Boolean> create(@RequestBody int id) {
        Optional<Course> course = courseService.findById(id);
        if(!course.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        User user = securityService.getUser();
        UserCourse userCourse = UserCourse.create(user, course.get());
        userCourseService.save(userCourse);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('USER_COURSE_DELETE')")
    public ResponseEntity<Boolean> delete(@RequestBody int id) {
        Optional<Course> course = courseService.findById(id);
        if(!course.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        User user = securityService.getUser();
        Optional<UserCourse> userCourse = course.get().getUserCourse(user.getId());
        if(!userCourse.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        userCourseService.delete(userCourse.get());
        return ResponseEntity.ok(true);
    }

    @PostMapping("/mark")
    @PreAuthorize("hasAuthority('USER_COURSE_MARK')")
    public ResponseEntity<Boolean> mark(@RequestBody UserCourseMarkDTO userCourseMarkDTO) {
        Optional<Course> course = courseService.findById(userCourseMarkDTO.getCourseId());
        Optional<User> student = userService.findById(userCourseMarkDTO.getStudentId());
        if(!course.isPresent() || !student.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        User user = securityService.getUser();
        if(course.get().getTeacher().getId() != user.getId()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<UserCourse> userCourse = student.get().getUserCourseByCourseId(course.get().getId());
        if(!userCourse.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        userCourse.get().setMark(userCourseMarkDTO.getMark());
        userService.update(student.get());
        return ResponseEntity.ok(true);
    }
}
