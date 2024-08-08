package mate.academy.springbootwebgreqit.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    @ToStringExclude
    @EqualsAndHashCode.Exclude
    private Set<Book> book = new HashSet<>();
}
