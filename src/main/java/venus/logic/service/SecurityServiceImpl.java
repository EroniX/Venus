package venus.logic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import venus.logic.model.User;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;

    Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public User getUser() {
        String username = getUsername();
        if(username != null) {
            return userService.findByUsername(username);
        }

        return null;
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
    public Boolean login(String username, String password) {
        if(username == null) {
            throw new IllegalArgumentException();
        }

        if(password == null) {
            throw new IllegalArgumentException();
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails == null) {
            return false;
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        password,
                        userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.warn(String.format("Auto login %s successfully!", username));
            return true;
        }

        return false;
    }

    @Override
    public Boolean logout() {
        if(isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(null);
            SecurityContextHolder.clearContext();
            return true;
        }

        return false;
    }
}
