package venus.dal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import venus.dal.model.Semester;

@Repository
public interface SemesterRepository extends CrudRepository<Semester, Integer> {
}

