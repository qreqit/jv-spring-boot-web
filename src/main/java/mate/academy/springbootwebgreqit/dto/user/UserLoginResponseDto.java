package mate.academy.springbootwebgreqit.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class UserLoginResponseDto {
    private final String token;
}
