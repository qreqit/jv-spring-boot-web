package mate.academy.springbootwebgreqit.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequestDto {
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
}
