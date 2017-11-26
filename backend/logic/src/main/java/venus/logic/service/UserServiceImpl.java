package venus.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import venus.dal.model.User;
import venus.dal.repository.UserRepository;
import venus.logic.dto.UserDTO;
import venus.logic.exceptions.EmailAlreadyUsedException;
import venus.logic.exceptions.UsernameAlreadyUsedException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private HashSet<GrantedAuthority> toGrantedAuthorities(List<String> roleNames) {
        return new HashSet<>(0);
        /*HashSet<GrantedAuthority> grantedAuthorities = new HashSet<>(roleNames.size());
        for (String roleName : roleNames) {
            grantedAuthorities.add(new SimpleGrantedAuthority(roleName));
        }
        return grantedAuthorities;*/
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    user.get().getUsername(),
                    user.get().getPassword(),
                    toGrantedAuthorities(user.get().getRoleNames()));
        }
        return null;
    }

    @Override
    public void save(UserDTO userDTO) throws UsernameAlreadyUsedException, EmailAlreadyUsedException {
        User user = userDTO.toUser();
        if(findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyUsedException();
        }
        if(findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        }
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email)  {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(int id) {
        return userRepository.findOne(id);
    }
}
