package mate.academy.springbootwebgreqit.repository;

import mate.academy.springbootwebgreqit.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Long, Order> {
}
