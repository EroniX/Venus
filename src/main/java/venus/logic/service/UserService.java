package venus.logic.service;

import venus.dal.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
    User findById(int id);
}
