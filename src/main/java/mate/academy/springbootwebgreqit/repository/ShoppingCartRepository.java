package mate.academy.springbootwebgreqit.repository;

import mate.academy.springbootwebgreqit.model.ShoppingCart;
import mate.academy.springbootwebgreqit.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
//    @EntityGraph(attributePaths = {"cartItems", "cartItems.book"})
//    @Query("SELECT sc FROM ShoppingCart  sc WHERE  sc.user.id = :userId")
    Optional<ShoppingCart> findByUser(User user);
}
