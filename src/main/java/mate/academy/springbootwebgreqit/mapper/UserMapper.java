package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.user.UserRegistrationRequestDto;
import mate.academy.springbootwebgreqit.dto.user.UserResponseDto;
import mate.academy.springbootwebgreqit.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toUser(UserRegistrationRequestDto requestDto);
}
