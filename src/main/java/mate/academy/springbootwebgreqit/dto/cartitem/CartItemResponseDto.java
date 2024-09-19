package mate.academy.springbootwebgreqit.dto.cartitem;

import lombok.Data;

@Data
public class CartItemResponseDto {
    private Long id;
    private Long shoppingCartId;
    private Long bookId;
    private Integer quantity;
}
