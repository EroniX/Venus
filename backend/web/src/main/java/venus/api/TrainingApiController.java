package venus.api;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import venus.dal.model.Training;
import venus.dal.model.User;
import venus.logic.dto.TrainingDTO;
import venus.logic.service.SecurityService;
import venus.logic.service.TrainingService;
import venus.logic.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/training")
public class TrainingApiController {
    @Autowired
    private UserService userService;
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/find-all")
    @PreAuthorize("hasAuthority('TRAINING_LIST')")
    public ResponseEntity<Iterable<TrainingDTO>> findAll() {
        User user = securityService.getUser();
        Iterable<TrainingDTO> trainingDTOs = trainingService.convertToDTOs(
            Lists.newArrayList(trainingService.findAll()),
            user);
        return ResponseEntity.ok(trainingDTOs);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('TRAINING_DELETE')")
    public ResponseEntity delete(@RequestBody int id) {
        trainingService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add/{id}")
    @PreAuthorize("hasAuthority('TRAINING_ADD')")
    public ResponseEntity add(@RequestBody Training training) {
        trainingService.save(training);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('TRAINING_REGISTER')")
    public ResponseEntity<Boolean> register(@RequestBody int id) {
        User user = securityService.getUser();
        Optional<Training> training = trainingService.findById(id);
        if(!training.isPresent()) {
            return ResponseEntity.ok(false);
        }
        Boolean result = userService.registerTraining(user, training.get());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/unregister")
    @PreAuthorize("hasAuthority('TRAINING_UNREGISTER')")
    public ResponseEntity<Boolean> unregister(@RequestBody int id) {
        User user = securityService.getUser();
        Optional<Training> training = trainingService.findById(id);
        if(!training.isPresent()) {
            return ResponseEntity.ok(false);
        }
        Boolean result = userService.unregisterTraining(user, id);
        return ResponseEntity.ok(result);
    }
}
