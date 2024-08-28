package mate.academy.springbootwebgreqit.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
<<<<<<< HEAD


=======
import lombok.Getter;
import lombok.Setter;

>>>>>>> Add_ShoppingCart_Model
@Data
public class UserLoginRequestDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
