package mate.academy.springbootwebgreqit.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCategoryRequestDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String description;
}
