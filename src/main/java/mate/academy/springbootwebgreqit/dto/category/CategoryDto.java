package mate.academy.springbootwebgreqit.dto.category;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapping;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
}
