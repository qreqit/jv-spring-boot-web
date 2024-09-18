package mate.academy.springbootwebgreqit.dto.orderItem;

import lombok.Data;

@Data
public class OrderItemResponseDto {
    private Long id;
    private Long bookId;
    private int quantity;
}