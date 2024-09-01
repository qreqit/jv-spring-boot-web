package mate.academy.springbootwebgreqit.repository;

import mate.academy.springbootwebgreqit.model.Order;
import mate.academy.springbootwebgreqit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUser(User user);
}
