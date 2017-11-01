package venus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import venus.logic.annotations.Authenticated;
import venus.logic.annotations.NotAuthenticated;
import venus.logic.model.User;
import venus.logic.service.SecurityService;
import venus.logic.service.UserService;
import venus.logic.validator.UserValidator;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;

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
    public ResponseEntity<Boolean> logout() {
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
            return ResponseEntity.badRequest().build();
        }

        userService.save(user);
        if (securityService.login(user.getUsername(), user.getPassword())) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.badRequest().build();
    }
}