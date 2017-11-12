package venus.dal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import venus.dal.model.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer> {
}

