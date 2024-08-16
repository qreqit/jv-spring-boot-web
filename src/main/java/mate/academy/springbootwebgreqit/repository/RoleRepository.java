package mate.academy.springbootwebgreqit.repository;

import mate.academy.springbootwebgreqit.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
