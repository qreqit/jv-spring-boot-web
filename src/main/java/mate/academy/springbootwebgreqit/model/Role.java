package mate.academy.springbootwebgreqit.model;

import jakarta.persistence.*;
import lombok.Data;
import mate.academy.springbootwebgreqit.roles.RoleName;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
public abstract class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

    @Override
    public String getAuthority() {
        return name.name();
    }
}
