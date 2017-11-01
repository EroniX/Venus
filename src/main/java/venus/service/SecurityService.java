package venus.service;

import org.springframework.security.core.userdetails.UserDetails;
import venus.model.User;

public interface SecurityService {
    User getUser();
    Boolean isAuthenticated();
    String getUsername();
    UserDetails getUserDetails();
    Boolean login(String username, String password);
    Boolean logout();
}
