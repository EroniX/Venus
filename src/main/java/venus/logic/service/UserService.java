package venus.logic.service;

import venus.logic.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
