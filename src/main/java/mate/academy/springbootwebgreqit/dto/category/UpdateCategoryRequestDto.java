package mate.academy.springbootwebgreqit.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCategoryRequestDto {
    @NotNull(message = "ID cannot be null")
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String description;
}
