package mate.academy.springbootwebgreqit.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateBookRequestDto {
    private String title;
    private String author;
    private BigDecimal price;
}
