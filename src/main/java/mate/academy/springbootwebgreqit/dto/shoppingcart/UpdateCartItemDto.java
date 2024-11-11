package mate.academy.springbootwebgreqit.dto.shoppingcart;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCartItemDto {
    @NotNull
    private int quantity;
}
