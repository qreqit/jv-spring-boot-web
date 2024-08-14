package mate.academy.springbootwebgreqit.dto.cartItem;

import lombok.Getter;
import lombok.Setter;
import mate.academy.springbootwebgreqit.model.Book;
import mate.academy.springbootwebgreqit.model.ShoppingCart;

@Getter
@Setter
public class CartItemDto {
    private Long id;
    private ShoppingCart shoppingCart;
    private Book book;
    private int quantity;
}
