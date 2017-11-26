package venus.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import venus.dal.model.Training;
import venus.dal.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {
    Iterable<Training> findByStudentsIsNotContaining(User user);
}

