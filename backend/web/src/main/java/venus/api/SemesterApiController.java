package venus.api;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import venus.dal.model.Semester;
import venus.dal.model.User;
import venus.logic.dto.SemesterDTO;
import venus.logic.service.SecurityService;
import venus.logic.service.SemesterService;
import venus.logic.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/semester")
public class SemesterApiController {
    @Autowired
    private UserService userService;
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('SEMESTER_LIST')")
    public ResponseEntity<List<SemesterDTO>> list() {
        User user = securityService.getUser();
        List<Semester> semesters = Lists.newArrayList(semesterService.findAll());
        return ResponseEntity.ok(
                semesterService.convertToDTOs(semesters, user));
    }

    @GetMapping("/current")
    @PreAuthorize("hasAuthority('SEMESTER_CURRENT')")
    public ResponseEntity<SemesterDTO> current() {
        User user = securityService.getUser();
        Optional<Semester> semester = semesterService.current();
        if(!semester.isPresent()) {
            return ResponseEntity.ok(null);
        }
        SemesterDTO semesterDTO = SemesterDTO.create(semester.get(), user);
        return ResponseEntity.ok(semesterDTO);
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('SEMESTER_REGISTER')")
    public ResponseEntity<Boolean> register() {
        User user = securityService.getUser();
        Optional<Semester> semester = semesterService.current();
        if(!semester.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        if(user.hasSemester(semester.get().getId())) {
            return ResponseEntity.ok(false);
        }
        user.addSemester(semester.get());
        userService.update(user);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/unregister")
    @PreAuthorize("hasAuthority('SEMESTER_UNREGISTER')")
    public ResponseEntity<Boolean> unregister() {
        User user = securityService.getUser();
        Optional<Semester> semester = semesterService.current();
        if(!semester.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        if(!user.hasSemester(semester.get().getId())) {
            return ResponseEntity.ok(false);
        }
        user.removeSemester(semester.get().getId());
        userService.update(user);
        return ResponseEntity.ok(true);
    }
}
