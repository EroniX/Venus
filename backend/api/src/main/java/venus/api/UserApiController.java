package venus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import venus.logic.dto.UserRegisterDTO;
import venus.logic.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserService userService;
/*
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
       if(securityService.logout(request)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
*/

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO params) {
        /*if (bindingResult.hasErrors()) {
            return new ResponseEntity<Error>(HttpStatus.EXPECTATION_FAILED);
        }*/
        int i = 0;
        /*String password = new String(user.getPassword());

        try {
            userService.save(user);
        }
        catch(UsernameAlreadyUsedException e) {
            return ResponseEntity.badRequest().build();
        }
        catch(EmailAlreadyUsedException e) {
            return ResponseEntity.badRequest().build();
        }

        /*if (securityService.login(user.getUsername(), password, request)) {
            return ResponseEntity.ok().build();
        }*/
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/check-username/{username}")
    public ResponseEntity<?> checkUsername(@PathVariable String username) {
        if(userService.findByUsername(username) == null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/check-email/{email}")
    public ResponseEntity checkEmail(@PathVariable String email) {
        if(userService.findByEmail(email) == null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}