package venus.dal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import venus.dal.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
