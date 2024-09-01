package mate.academy.springbootwebgreqit.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import mate.academy.springbootwebgreqit.model.Order;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class OrderRequestDto {
    @NotNull
    private Long userId;
    @NotNull
    private Order.Status status;
    @NotNull
    private BigDecimal total;
    @NotNull
    private Set<@Positive Long> orderItems;
}
