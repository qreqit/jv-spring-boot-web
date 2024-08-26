package mate.academy.springbootwebgreqit.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.user.UserRegistrationRequestDto;
import mate.academy.springbootwebgreqit.dto.user.UserResponseDto;
import mate.academy.springbootwebgreqit.exception.RegistrationException;
import mate.academy.springbootwebgreqit.mapper.UserMapper;
import mate.academy.springbootwebgreqit.model.User;
import mate.academy.springbootwebgreqit.repository.UserRepository;
import mate.academy.springbootwebgreqit.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException("Can't register user with email: " + requestDto.getEmail());
        }
        User user = userMapper.toUser(requestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
