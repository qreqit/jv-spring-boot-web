package mate.academy.springbootwebgreqit.repository;

import mate.academy.springbootwebgreqit.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
