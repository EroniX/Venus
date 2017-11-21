package venus.logic.service;

import venus.dal.model.User;
import venus.logic.exceptions.EmailAlreadyUsedException;
import venus.logic.exceptions.UsernameAlreadyUsedException;

import java.util.Optional;

public interface UserService extends org.springframework.security.core.userdetails.UserDetailsService {
    void save(User user) throws EmailAlreadyUsedException, UsernameAlreadyUsedException;
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    User findById(int id);
}
