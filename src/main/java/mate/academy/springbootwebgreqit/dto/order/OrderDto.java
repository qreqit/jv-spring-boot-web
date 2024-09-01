package mate.academy.springbootwebgreqit.dto.order;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import mate.academy.springbootwebgreqit.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class OrderDto {
    private Order.Status status;
    private BigDecimal total;
    private LocalDateTime orderDate;
    private Set<@Positive Long> orderItems;
}
