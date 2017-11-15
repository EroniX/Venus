package venus.logic.service;

import venus.dal.model.User;
import venus.logic.exceptions.EmailAlreadyUsedException;
import venus.logic.exceptions.UsernameAlreadyUsedException;

import java.util.List;

public interface UserService {
    void save(User user) throws EmailAlreadyUsedException, UsernameAlreadyUsedException;
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(int id);
}
