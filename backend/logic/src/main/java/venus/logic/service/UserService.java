package venus.logic.service;

import venus.dal.model.Semester;
import venus.dal.model.Training;
import venus.dal.model.User;
import venus.logic.dto.UserDTO;
import venus.logic.exceptions.EmailAlreadyUsedException;
import venus.logic.exceptions.UsernameAlreadyUsedException;

import java.util.Optional;

public interface UserService extends org.springframework.security.core.userdetails.UserDetailsService {
    void save(UserDTO userDTO) throws EmailAlreadyUsedException, UsernameAlreadyUsedException;

    Boolean registerSemester(User user, Semester semester);
    Boolean unregisterSemester(User user, int semesterId);

    Boolean registerTraining(User user, Training training);
    Boolean unregisterTraining(User user, int trainingId);

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findById(int id);

    void update(User user);
}
