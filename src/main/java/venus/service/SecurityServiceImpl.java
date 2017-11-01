package venus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import venus.model.Role;
import venus.model.User;
import venus.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Service
    public class UserDetailsServiceImpl implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Override
        @Transactional(readOnly = true)
        public UserDetails loadUserByUsername(String username) {
            if(username == null) {
                throw new IllegalArgumentException();
            }

            User user = userRepository.findByUsername(username);
            if(user == null) {
                return null;
            }

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

            if(user.HasRoles()) {
                for (Role role : user.getRoles()) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
                }
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    grantedAuthorities);
        }
    }

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;

    //private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

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

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            //logger.debug(String.format("Auto login %s successfully!", username));
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
