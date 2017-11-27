package venus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import venus.logic.dto.UserDTO;
import venus.logic.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserService userService;

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