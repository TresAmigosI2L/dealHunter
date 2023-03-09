package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.models.dto.auth.AuthRequestDto;
import fr.ulco.dealhunter.models.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;

    public String generateToken(AuthRequestDto authRequest) {
        return this.generateToken(authRequest.getUsername(), authRequest.getPassword());
    }

    private String generateToken(String username, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
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

    public UserEntity authenticateUser(AuthRequestDto authRequest) {
        return this.authenticateUser(authRequest.getUsername(), authRequest.getPassword());
    }

    private UserEntity authenticateUser(String username, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        return (UserEntity) authentication.getPrincipal();
    }
}
