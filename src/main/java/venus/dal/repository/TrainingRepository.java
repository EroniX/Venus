package venus.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import venus.dal.model.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {
}

