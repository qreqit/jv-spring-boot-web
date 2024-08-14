package mate.academy.springbootwebgreqit.dto.shoppingCart;

import lombok.Getter;
import lombok.Setter;
import mate.academy.springbootwebgreqit.model.CartItem;
import mate.academy.springbootwebgreqit.model.User;
import java.util.Set;

@Getter
@Setter
public class ShoppingCartDto {
    private Long id;
    private User user;
    private Set<CartItem> cartItems;
}
