package mate.academy.springbootwebgreqit.dto.order;

import lombok.Data;
import mate.academy.springbootwebgreqit.dto.orderitem.OrderItemResponseDto;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private List<OrderItemResponseDto> orderItems;
    private String orderDate;
    private BigDecimal total;
    private String status;
}
