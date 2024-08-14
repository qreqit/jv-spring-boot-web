package mate.academy.springbootwebgreqit.repository;

import mate.academy.springbootwebgreqit.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
