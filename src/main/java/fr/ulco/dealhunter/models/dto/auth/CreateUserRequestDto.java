package fr.ulco.dealhunter.models.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO to create a {@link fr.ulco.dealhunter.models.entities.TODO} entity
 */
@Data
public class CreateUserRequestDto implements Serializable {
    @NotBlank @Email
    String username;

    @NotBlank
    String password;

    @NotBlank
    String confirmPassword;

    Set<String> authorities;
}
