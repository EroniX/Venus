package venus.api;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import venus.dal.model.Subject;
import venus.dal.model.User;
import venus.logic.dto.SubjectDTO;
import venus.logic.service.SecurityService;
import venus.logic.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectApiController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/find-all")
    @PreAuthorize("hasAuthority('SUBJECT_LIST')")
    public ResponseEntity<Iterable<SubjectDTO>> findAll() {
        User user = securityService.getUser();
        List<SubjectDTO> subjectDTOs = subjectService.convertToDTOs(
                Lists.newArrayList(subjectService.findAll()),
                user);

        return ResponseEntity.ok(subjectDTOs);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('SUBJECT_TEACHER_DELETE')")
    public ResponseEntity delete(@RequestBody int id) {
        subjectService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create/")
    @PreAuthorize("hasAuthority('SUBJECT_TEACHER_CREATE')")
    public ResponseEntity create(@RequestBody Subject subject) {
        subjectService.save(subject);
        return ResponseEntity.ok().build();
    }
}
