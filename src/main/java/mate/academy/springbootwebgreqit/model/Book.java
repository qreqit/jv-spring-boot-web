package mate.academy.springbootwebgreqit.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE books SET is_deleted = true where id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 50)
    private String author;
    @Column(nullable = false, unique = true, length = 13)
    private String isbn;
    @Column(nullable = false, precision = 10, scale = 2)
    @Min(0)
    private BigDecimal price;
    @Column(nullable = false, length = 2000)
    private String description;
    @Column(nullable = false, length = 255)
    private String coverImage;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @ToStringExclude
    @EqualsAndHashCode.Exclude
    private Set<Category> categories = new HashSet<>();
    private boolean isDeleted = false;
}
