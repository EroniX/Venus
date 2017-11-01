package venus.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import venus.logic.model.Semester;

@Repository
public interface SemesterRepository extends CrudRepository<Semester, Integer> {
}

