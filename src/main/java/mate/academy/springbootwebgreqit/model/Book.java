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
    @Column(nullable = false, length = 50)
    private String author;
    @Column(nullable = false, unique = true, length = 13)
    private String isbn;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @Column(nullable = false, length = 2000)
    private String description;
    @Column(nullable = false, length = 255)
    private String coverImage;
    private boolean isDeleted = false;
}
