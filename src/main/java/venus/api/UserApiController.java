package venus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import venus.logic.annotations.Authenticated;
import venus.logic.annotations.NotAuthenticated;
import venus.dal.model.User;
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

        userService.save(user);
        if (securityService.login(user.getUsername(), user.getPassword(), request)) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.badRequest().build();
    }
}