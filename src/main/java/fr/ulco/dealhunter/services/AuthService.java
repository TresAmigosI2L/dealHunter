package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.models.dto.auth.AuthRequestDto;
import fr.ulco.dealhunter.models.dto.auth.UserResponseDto;
import fr.ulco.dealhunter.models.entities.UserEntity;
import fr.ulco.dealhunter.models.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final UserMapper userMapper;

    public String generateToken(AuthRequestDto authRequest) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        var user = (UserEntity) authentication.getPrincipal();

        var now = Instant.now();
        long expiry = 36000L;

        var scope =
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(joining(" "));

        var claims =
                JwtClaimsSet.builder()
                        .issuedAt(now)
                        .expiresAt(now.plusSeconds(expiry))
                        .subject(format("%s:%s", user.getId(), user.getUsername()))
                        .claim("id", user.getId())
                        .claim("roles", scope)
                        .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public UserResponseDto authenticateUser(AuthRequestDto authRequest) throws BadCredentialsException {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        var userEntity = (UserEntity) authentication.getPrincipal();
        return userMapper.toOutputDto(userEntity);
    }

    public String getUsernameOfAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return Arrays.stream(authentication.getName().split(":"))
                .skip(1)
                .findFirst()
                .orElse(null);
    }
}
