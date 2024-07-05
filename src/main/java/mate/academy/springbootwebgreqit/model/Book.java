package mate.academy.springbootwebgreqit.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE books SET is_deleted = true where id = ?")
@SQLRestriction("is_deleted = false")@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    @NotNull
    private String title;
    @Column(length = 50)
    @NotNull
    private String author;
    @Column(unique = true, length = 13)
    @NotNull
    private String isbn;
    @Column(precision = 10, scale = 2)
    @NotNull
    @Min(0)
    private BigDecimal price;
    @Column(length = 2000)
    @NotNull
    private String description;
    @Column(length = 255)
    @NotNull
    private String coverImage;
    private boolean isDeleted = false;
}
