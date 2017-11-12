package venus.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import venus.dal.model.User;
import venus.logic.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @Override
    public User getUser() {
        return userService.findByUsername(getUsername());
    }

    @Override
    public Boolean isAuthenticated() {
        return getUsername() != null;
    }

    @Override
    public String getUsername() {
        UserDetails userDetails = getUserDetails();
        if(userDetails != null) {
            return userDetails.getUsername();
        }

        return null;
    }

    @Override
    public UserDetails getUserDetails() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails);
        }

        return null;
    }

    @Override
    public Boolean login(String username, String password, HttpServletRequest request) {
        if(username == null) {
            throw new IllegalArgumentException();
        }
        if(password == null) {
            throw new IllegalArgumentException();
        }
        if(request == null) {
            throw new IllegalArgumentException();
        }

        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password);

            token.setDetails(new WebAuthenticationDetails(request));

            Authentication authentication = this.authenticationManager.authenticate(token);

            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return true;
            }
            return false;
        }
        catch(Exception e) {
            // @TODO: Logging
            return false;
        }
    }

    @Override
    public Boolean logout(HttpServletRequest request) {
        if(request == null) {
            throw new IllegalArgumentException();
        }

        try {
            new SecurityContextLogoutHandler().logout(request, null, null);
            return true;
        }
        catch(Exception e) {
            // @TODO: Logging
            return false;
        }
    }
}
