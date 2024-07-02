package mate.academy.springbootwebgreqit.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE books SET is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String title;
    @Column(length = 50)
    private String author;
    @Column(unique = true, length = 13)
    private String isbn;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    @Column(length = 2000)
    private String description;
    @Column(length = 255)
    private String coverImage;
    @Column(nullable = false)
    private boolean isDeleted = false;

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
