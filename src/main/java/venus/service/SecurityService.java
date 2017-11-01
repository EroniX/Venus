package venus.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityService {
    Boolean isLoggedIn();
    String findUsername();
    Boolean autoLogin(String username, String password);
}
