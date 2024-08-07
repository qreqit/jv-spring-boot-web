package mate.academy.springbootwebgreqit.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.user.UserLoginRequestDto;
import mate.academy.springbootwebgreqit.dto.user.UserLoginResponseDto;
import mate.academy.springbootwebgreqit.dto.user.UserRegistrationRequestDto;
import mate.academy.springbootwebgreqit.dto.user.UserResponseDto;
import mate.academy.springbootwebgreqit.security.AuthenticationService;
import mate.academy.springbootwebgreqit.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public UserResponseDto registerUser(@RequestBody UserRegistrationRequestDto requestBody) {
        return userService.register(requestBody);
    }

    @PostMapping("/login")
    public UserLoginResponseDto loginUser(@RequestBody UserLoginRequestDto response) {
        return authenticationService.authenticate(response);
    }
}

