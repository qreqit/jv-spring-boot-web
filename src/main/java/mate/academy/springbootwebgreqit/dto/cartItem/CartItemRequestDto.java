package mate.academy.springbootwebgreqit.dto.cartItem;

import lombok.Data;

@Data
public class CartItemRequestDto {
    private Long shoppingCartId;
    private Long bookId;
    private Integer quantity;
}
