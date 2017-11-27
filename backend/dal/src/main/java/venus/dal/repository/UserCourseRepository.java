package venus.dal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import venus.dal.model.UserCourse;

@Repository
public interface UserCourseRepository extends CrudRepository<UserCourse, Integer> {
}
