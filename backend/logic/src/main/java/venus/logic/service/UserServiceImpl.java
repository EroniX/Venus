package venus.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import venus.dal.model.User;
import venus.dal.repository.UserRepository;
import venus.logic.exceptions.EmailAlreadyUsedException;
import venus.logic.exceptions.UsernameAlreadyUsedException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByUsername(username);
        if(user.get() != null) {
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), new ArrayList<>());

        }
        return null;
        //final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
        //user.ifPresent(detailsChecker::check);
        //return user.orElseThrow(() -> new UsernameNotFoundException("user not found."));
    }

    @Override
    public void save(User user) throws UsernameAlreadyUsedException, EmailAlreadyUsedException {
        /*if(findByUsername(user.getUsername()) != null) {
            throw new UsernameAlreadyUsedException();
        }
        if(findByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyUsedException();
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);*/
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
