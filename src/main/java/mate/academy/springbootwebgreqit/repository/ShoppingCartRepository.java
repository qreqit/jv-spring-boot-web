package mate.academy.springbootwebgreqit.repository;

import mate.academy.springbootwebgreqit.model.CartItem;
import mate.academy.springbootwebgreqit.model.ShoppingCart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @EntityGraph(attributePaths = {"cartItems", "cartItems.book"})
    Optional<ShoppingCart> findByUserId(Long userId);

    Optional<ShoppingCart> findByCartItems(Set<CartItem> cartItems);
}
