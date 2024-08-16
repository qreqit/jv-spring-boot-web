package mate.academy.springbootwebgreqit.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class UserLoginResponseDto {
    private String token;
}
