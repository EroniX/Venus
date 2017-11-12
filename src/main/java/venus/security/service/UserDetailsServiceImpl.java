package venus.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import venus.dal.model.Role;
import venus.dal.model.User;
import venus.dal.repository.UserRepository;
import venus.security.model.VenusUserDetails;

import java.util.HashSet;
import java.util.Set;

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

        if(user.hasRoles()) {
            for (Role role : user.getRoles()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }

        return new VenusUserDetails(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities);
    }
}