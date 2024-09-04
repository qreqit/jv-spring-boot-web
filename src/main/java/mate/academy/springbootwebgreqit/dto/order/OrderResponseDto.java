package mate.academy.springbootwebgreqit.dto.order;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import mate.academy.springbootwebgreqit.dto.orderItem.OrderItemDto;
import mate.academy.springbootwebgreqit.dto.orderItem.OrderItemResponseDto;
import mate.academy.springbootwebgreqit.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private List<OrderItemResponseDto> orderItems;
    private String orderDate;
    private BigDecimal total;
    private String status;
}