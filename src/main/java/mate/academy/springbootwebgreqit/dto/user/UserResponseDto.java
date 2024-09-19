package mate.academy.springbootwebgreqit.dto.user;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String shippingAddress;
}
