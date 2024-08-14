package mate.academy.springbootwebgreqit.repository;

import mate.academy.springbootwebgreqit.model.ShoppingCart;
import mate.academy.springbootwebgreqit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByUser(User user);
}