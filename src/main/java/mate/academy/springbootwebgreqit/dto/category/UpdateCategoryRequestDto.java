package mate.academy.springbootwebgreqit.dto.category;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UpdateCategoryRequestDto {
    private Long id;
    private String name;
}
