package venus.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import venus.dal.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}

