package venus.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import venus.logic.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
}

