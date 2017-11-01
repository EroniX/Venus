package venus.logic.service;

import org.springframework.security.core.userdetails.UserDetails;
import venus.logic.model.User;

public interface SecurityService {
    User getUser();
    Boolean isAuthenticated();
    String getUsername();
    UserDetails getUserDetails();
    Boolean login(String username, String password);
    Boolean logout();
}
