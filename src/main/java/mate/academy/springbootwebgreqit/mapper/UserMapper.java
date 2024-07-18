package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.user.UserResponseDto;
import mate.academy.springbootwebgreqit.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);
}
