package venus.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import venus.logic.model.Training;

@Repository
public interface TrainingRepository extends CrudRepository<Training, Integer> {
}

