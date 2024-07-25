package mate.academy.springbootwebgreqit.repository;

import mate.academy.springbootwebgreqit.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class RoleRepository implements JpaRepository<Role, Long> {
}
