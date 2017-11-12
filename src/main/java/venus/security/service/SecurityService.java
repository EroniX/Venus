package venus.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import venus.dal.model.User;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
    User getUser();
    Boolean isAuthenticated();
    String getUsername();
    UserDetails getUserDetails();
    Boolean login(String username, String password, HttpServletRequest request);
    Boolean logout(HttpServletRequest request);
}
