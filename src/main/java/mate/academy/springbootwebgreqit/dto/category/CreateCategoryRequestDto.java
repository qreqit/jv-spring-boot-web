package mate.academy.springbootwebgreqit.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CreateCategoryRequestDto {
    private String name;
    private String description;
}
