package mate.academy.springbootwebgreqit.dto.order;

import lombok.Data;
import mate.academy.springbootwebgreqit.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class OrderResponseDto {
    private Long id;
    private Order.Status status;
    private BigDecimal total;
    private LocalDateTime orderDate;
    private List<Long> orderItems;
}