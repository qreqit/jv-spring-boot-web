package mate.academy.springbootwebgreqit.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


public class UpdateBookRequestDto {
    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
