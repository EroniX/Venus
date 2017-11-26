package venus.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import venus.dal.model.User;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private UserService userService;

    @Override
    public User getUser() {
        String username = getUsername();
        if(username != null) {
            return userService.findByUsername(username).get();
        }

        return null;
    }

    @Override
    public Boolean isAuthenticated() {
        return getUsername() != null;
    }

    @Override
    public String getUsername() {
        Object username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if(username instanceof String) {
            return (String)username;
        }
        return null;
    }
}