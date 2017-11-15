package venus.logic.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import venus.dal.model.User;
import venus.dal.repository.RoleRepository;
import venus.dal.repository.UserRepository;
import venus.logic.exceptions.EmailAlreadyUsedException;
import venus.logic.exceptions.UsernameAlreadyUsedException;

@Service
@SessionScope
@Data
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) throws UsernameAlreadyUsedException, EmailAlreadyUsedException {
        if(findByUsername(user.getUsername()) != null) {
            throw new UsernameAlreadyUsedException();
        }
        if(findByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyUsedException();
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email)  {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(int id) {
        return userRepository.findOne(id);
    }
}
