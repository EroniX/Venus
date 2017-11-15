package venus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import venus.logic.annotations.Authenticated;
import venus.logic.annotations.NotAuthenticated;
import venus.dal.model.User;
import venus.logic.exceptions.EmailAlreadyUsedException;
import venus.logic.exceptions.UsernameAlreadyUsedException;
import venus.security.service.SecurityService;
import venus.logic.service.UserService;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    @NotAuthenticated
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user, HttpServletRequest request) {
        if(securityService.login(user.getUsername(), user.getPassword(), request)) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.badRequest().build();
    }

    @Authenticated
    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(HttpServletRequest request) {
        if(securityService.logout(request)) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.badRequest().build();
    }

    @NotAuthenticated
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        String password = new String(user.getPassword());

        try {
            userService.save(user);
        }
        catch(UsernameAlreadyUsedException e) {
            return ResponseEntity.badRequest().build();
        }
        catch(EmailAlreadyUsedException e) {
            return ResponseEntity.badRequest().build();
        }

        if (securityService.login(user.getUsername(), password, request)) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }

    @NotAuthenticated
    @PostMapping("/check-username/{username}")
    public ResponseEntity checkUsername(@RequestParam String username) {
        if(userService.findByUsername(username) == null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @NotAuthenticated
    @PostMapping("/check-email/{username}")
    public ResponseEntity checkEmail(@RequestParam String email) {
        if(userService.findByEmail(email) == null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}