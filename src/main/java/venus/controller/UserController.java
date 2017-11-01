package venus.controller;

//import venus.logic.service.SecurityService;
import venus.logic.service.SecurityService;
import venus.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;
import venus.logic.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
}
