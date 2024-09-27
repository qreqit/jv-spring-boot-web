package mate.academy.springbootwebgreqit.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCategoryRequestDto {
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name cannot be empty")
    private String name;
    private String description;
}
