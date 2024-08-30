package mate.academy.springbootwebgreqit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart_Items")
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    @JsonIgnore
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    private int quantity;
}
