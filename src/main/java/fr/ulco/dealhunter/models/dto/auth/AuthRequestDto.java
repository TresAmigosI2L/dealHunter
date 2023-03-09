package fr.ulco.dealhunter.models.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO to authenticate to a {@link fr.ulco.dealhunter.models.entities.UserEntity} entity
 */
@Data
public class AuthRequestDto implements Serializable {
    @NotNull @Email
    private String username;
    @NotNull
    private String password;
}