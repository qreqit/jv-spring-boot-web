package mate.academy.springbootwebgreqit.security;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.user.UserLoginRequestDto;
import mate.academy.springbootwebgreqit.dto.user.UserLoginResponseDto;
import mate.academy.springbootwebgreqit.model.User;
import mate.academy.springbootwebgreqit.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Transactional
    public UserLoginResponseDto authenticate(UserLoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Hibernate.initialize(user.getRoles());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        String token = jwtUtil.generateToken(request.getEmail());
        return new UserLoginResponseDto(token);
    }
}
