package mate.academy.springbootwebgreqit.dto.cartItem;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mate.academy.springbootwebgreqit.model.Book;
import mate.academy.springbootwebgreqit.model.ShoppingCart;

@Data
public class CartItemDto {
    private Long id;
    private ShoppingCart shoppingCart;
    private Book book;
    private int quantity;
}
