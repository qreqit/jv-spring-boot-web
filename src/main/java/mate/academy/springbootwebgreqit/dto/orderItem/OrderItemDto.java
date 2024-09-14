package mate.academy.springbootwebgreqit.dto.orderItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemDto {
    @NotNull
    private Long orderId;
    @NotNull
    private Long bookId;
    @Positive
    private int quantity;
    @Positive
    private BigDecimal price;
}
