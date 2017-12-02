package venus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import venus.dal.model.User;
import venus.logic.dto.UserDTO;
import venus.logic.service.SecurityService;
import venus.logic.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/find-by-id/{userId}")
    public ResponseEntity<UserDTO> findById(@PathVariable int userId) {
        Optional<User> user = userService.findById(userId);
        if(!user.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        UserDTO userDTO = UserDTO.create(user.get());
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/find-me")
    public ResponseEntity<UserDTO> findMe() {
        User user = securityService.getUser();
        UserDTO userDTO = UserDTO.create(user);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            userService.save(userDTO);
            return ResponseEntity.ok().build();
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/validate-username/{username}")
    public ResponseEntity<Boolean> validateUsername(@PathVariable String username) {
        return ResponseEntity.ok(
            !userService.findByUsername(username).isPresent());
    }

    @GetMapping("/validate-email/{email}")
    public ResponseEntity<Boolean> validateEmail(@PathVariable String email) {
        return ResponseEntity.ok(
            !userService.findByEmail(email).isPresent());
    }
}