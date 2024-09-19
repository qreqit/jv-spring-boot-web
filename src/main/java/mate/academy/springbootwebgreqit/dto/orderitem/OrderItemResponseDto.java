package mate.academy.springbootwebgreqit.dto.orderitem;

import lombok.Data;

@Data
public class OrderItemResponseDto {
    private Long id;
    private Long bookId;
    private int quantity;
}
