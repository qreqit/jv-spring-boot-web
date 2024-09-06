package mate.academy.springbootwebgreqit.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Table(name = "roles")
@Entity
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName name;

    @Override
    public String getAuthority() {
        return name.name();
    }

    public enum RoleName {
        USER,
        ADMIN
    }
}
