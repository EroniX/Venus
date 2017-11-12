package venus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import venus.dal.model.Training;
import venus.dal.model.User;
import venus.logic.service.TrainingService;
import venus.security.service.SecurityService;

@RestController
@RequestMapping("/api/training")
public class TrainingApiController {
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private SecurityService securityService;

    @GetMapping
    @PreAuthorize("hasAuthority('TRAINING_LIST')")
    public ResponseEntity<Iterable<Training>> list() {
        return ResponseEntity.ok(trainingService.findAll());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TRAINING_DELETE')")
    public ResponseEntity delete(@RequestBody int id) {
        trainingService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('TRAINING_ADD')")
    public ResponseEntity add(@RequestBody Training training) {
        trainingService.save(training);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/{id}")
    @PreAuthorize("hasAuthority('TRAINING_REGISTER')")
    public ResponseEntity register(@PathVariable int id) {
        User user = securityService.getUser();
        Training training = trainingService.findById(id);
        user.addTraining(training);
        return ResponseEntity.ok().build();
    }
}
