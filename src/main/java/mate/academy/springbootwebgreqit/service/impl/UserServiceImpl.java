package mate.academy.springbootwebgreqit.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.user.UserRegistrationRequestDto;
import mate.academy.springbootwebgreqit.dto.user.UserResponseDto;
import mate.academy.springbootwebgreqit.exception.RegistrationException;
import mate.academy.springbootwebgreqit.mapper.UserMapper;
import mate.academy.springbootwebgreqit.model.Role;
import mate.academy.springbootwebgreqit.model.ShoppingCart;
import mate.academy.springbootwebgreqit.model.User;
import mate.academy.springbootwebgreqit.repository.RoleRepository;
import mate.academy.springbootwebgreqit.repository.ShoppingCartRepository;
import mate.academy.springbootwebgreqit.repository.UserRepository;
import mate.academy.springbootwebgreqit.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException("Can't register user with email: " + requestDto.getEmail());
        }

        User user = userMapper.toUser(requestDto);
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(Role.RoleName.ADMIN)
                        .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        roles.add(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        User savedUser = userRepository.save(user);

        ShoppingCart shoppingCartForUser = new ShoppingCart();
        shoppingCartForUser.setUser(savedUser);
        ShoppingCart savedShoppingCart = shoppingCartRepository.save(shoppingCartForUser);

        savedUser.setShoppingCart(savedShoppingCart);
        User finalSavedUser = userRepository.save(savedUser);

        return userMapper.toDto(finalSavedUser);
    }
}
