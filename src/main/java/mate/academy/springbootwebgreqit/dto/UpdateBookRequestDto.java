package mate.academy.springbootwebgreqit.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookRequestDto {
    private String title;
    private String author;
}
