package fr.ulco.dealhunter.models.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO to create a {@link fr.ulco.dealhunter.models.entities.UserEntity} entity
 */
@Data
public class CreateUserRequestDto implements Serializable {
    @NotBlank @Email
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    private Set<String> authorities;
}
