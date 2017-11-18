package venus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import venus.dal.model.Subject;
import venus.logic.service.SubjectService;
import venus.security.service.SecurityService;

@RestController
@RequestMapping("/api/subject")
public class SubjectApiController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SecurityService securityService;

    @GetMapping
    @PreAuthorize("hasAuthority('SUBJECT_LIST')")
    public ResponseEntity<Iterable<Subject>> list() {
        return ResponseEntity.ok(subjectService.findAll());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('SUBJECT_TEACHER_DELETE')")
    public ResponseEntity delete(@RequestBody int id) {
        subjectService.delete(id);
        // @TODO: Probably need to delete the Courses and UserCourses as well
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create/")
    @PreAuthorize("hasAuthority('SUBJECT_TEACHER_CREATE')")
    public ResponseEntity create(@RequestBody Subject subject) {
        subjectService.save(subject);
        return ResponseEntity.ok().build();
    }
}
