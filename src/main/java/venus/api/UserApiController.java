package venus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import venus.logic.model.User;
import venus.logic.service.SecurityService;
import venus.logic.service.UserService;
import venus.logic.annotations.Authenticated;
import venus.logic.annotations.NotAuthenticated;
import venus.logic.annotations.Role;
import venus.logic.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping
    public ResponseEntity<User> user() {
        if (securityService.isAuthenticated()) {
            return ResponseEntity.badRequest().build();
        }

        User user = userService.findByUsername(
                securityService.getUsername());

        if(user != null) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.badRequest().build();
    }

    @NotAuthenticated
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        if(securityService.login(user.getUsername(), user.getPassword())) {
            return ResponseEntity.ok(user);
        }
        
        return ResponseEntity.badRequest().build();
    }

    @Authenticated
    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
        if(securityService.logout()) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.badRequest().build();
    }

    @NotAuthenticated
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build(); // @TODO: Proper error message
        }

        userService.save(user);
        if(securityService.login(user.getUsername(), user.getPassword())) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.badRequest().build();
    }

    @Role("can-check")
    @PostMapping("/check")
    public ResponseEntity<String> check() {
        return ResponseEntity.ok(securityService.getUsername());
    }
}