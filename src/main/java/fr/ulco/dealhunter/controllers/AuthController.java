package fr.ulco.dealhunter.controllers;

import fr.ulco.dealhunter.models.dto.auth.AuthRequestDto;
import fr.ulco.dealhunter.models.dto.auth.CreateUserRequestDto;
import fr.ulco.dealhunter.models.dto.auth.UserResponseDto;
import fr.ulco.dealhunter.services.AuthService;
import fr.ulco.dealhunter.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    // We can secure an endpoint with the @Secured("ADMIN") annotation

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody @Valid AuthRequestDto authRequest) {
        var token = authService.generateToken(authRequest);
        var user = authService.authenticateUser(authRequest);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, format("Bearer %s", token))
                .body(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid CreateUserRequestDto userRequest) {
        var user = userService.create(userRequest);
        return ResponseEntity.ok(user);
    }
}