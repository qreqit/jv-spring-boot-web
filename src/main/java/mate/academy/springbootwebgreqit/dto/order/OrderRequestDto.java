package mate.academy.springbootwebgreqit.dto.order;

import lombok.Data;
import mate.academy.springbootwebgreqit.model.Order;

@Data
public class OrderRequestDto {
    private Order.Status status;
}
