package mate.academy.springbootwebgreqit.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.user.UserRegistrationRequestDto;
import mate.academy.springbootwebgreqit.dto.user.UserResponseDto;
import mate.academy.springbootwebgreqit.exception.RegistrationException;
import mate.academy.springbootwebgreqit.mapper.UserMapper;
import mate.academy.springbootwebgreqit.model.User;
import mate.academy.springbootwebgreqit.repository.UserRepository;
import mate.academy.springbootwebgreqit.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Can't register user");
        }
        if (!requestDto.getPassword().equals(requestDto.getRepeatPassword())) {
            throw new RegistrationException("Passwords do not match");
        }
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setShippingAddress(requestDto.getShippingAddress());
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
