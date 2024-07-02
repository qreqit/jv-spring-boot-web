package mate.academy.springbootwebgreqit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookRequestDto {
    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
