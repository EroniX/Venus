package venus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import venus.dal.model.User;
import venus.logic.exceptions.EmailAlreadyUsedException;
import venus.logic.exceptions.UsernameAlreadyUsedException;
import venus.logic.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserService userService;

    /*@PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        if(securityService.logout(request)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
*/

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Error>(HttpStatus.EXPECTATION_FAILED);
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

        return ResponseEntity.ok().build();
        /*if (securityService.login(user.getUsername(), password, request)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();*/
    }

    @PostMapping("/check-username/{username}")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
        if(userService.findByUsername(username) == null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/check-email/{username}")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        if(userService.findByEmail(email) == null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}