package venus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import venus.model.User;
import venus.service.SecurityService;
import venus.service.UserService;
import venus.validator.UserValidator;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

 /*   @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }
*/
    @GetMapping
    public ResponseEntity<User> user() {
        if (!securityService.isLoggedIn()) {
            return ResponseEntity.badRequest().build();
        }

        User user = userService.findByUsername(
                securityService.findUsername());

        if(user != null) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        if(securityService.autoLogin(user.getUsername(), user.getPassword())) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        // @TODO: user validation
        //userValidator.validate(user, bindingResult);
/*
        if (bindingResult.hasErrors()) {
            return "registration";
        }*/

        userService.save(user);
        if(securityService.autoLogin(user.getUsername(), user.getPassword())) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.badRequest().build();
    }
}