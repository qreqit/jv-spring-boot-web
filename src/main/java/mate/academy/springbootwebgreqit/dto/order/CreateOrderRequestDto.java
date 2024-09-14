package mate.academy.springbootwebgreqit.dto.order;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateOrderRequestDto {
    @NotBlank
    @Max(40)
    private String shippingAddress;
}
