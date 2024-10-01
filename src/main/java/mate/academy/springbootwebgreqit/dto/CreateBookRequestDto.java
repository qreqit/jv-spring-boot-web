package mate.academy.springbootwebgreqit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateBookRequestDto {
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Author cannot be empty")
    private String author;
    @NotBlank(message = "ISBN cannot be empty")
    private String isbn;
    @NotNull(message = "Price cannot be null")
    private BigDecimal price;
    private String description;
    private String coverImage;
    @NotEmpty(message = "Categories cannot be empty")
    private List<@Positive Long> categoriesIds;
}
