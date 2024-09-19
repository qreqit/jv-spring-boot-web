package mate.academy.springbootwebgreqit.dto.shoppingcart;

import lombok.Data;
import mate.academy.springbootwebgreqit.model.CartItem;
import mate.academy.springbootwebgreqit.model.User;
import java.util.Set;

@Data
public class ShoppingCartDto {
    private Long id;
    private User user;
    private Set<CartItem> cartItems;
}
