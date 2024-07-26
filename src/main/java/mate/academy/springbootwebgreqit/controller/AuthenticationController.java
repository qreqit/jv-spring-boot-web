package mate.academy.springbootwebgreqit.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.user.UserRegistrationRequestDto;
import mate.academy.springbootwebgreqit.dto.user.UserResponseDto;
import mate.academy.springbootwebgreqit.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/registration")
    public UserResponseDto registerUser(@RequestBody UserRegistrationRequestDto requestBody) {
        return userService.register(requestBody);
    }
}

