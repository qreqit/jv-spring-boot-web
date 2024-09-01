package mate.academy.springbootwebgreqit.dto.orderItem;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemResponseDto {
    private Long id;
    private Long orderId;
    private Long bookId;
    private int quantity;
    private BigDecimal price;
}