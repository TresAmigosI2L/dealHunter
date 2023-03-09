package fr.ulco.dealhunter.models.dto.auth;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO to create a {@link fr.ulco.dealhunter.models.entities.TODO} entity
 */
@Data
public class UserResponseDto implements Serializable {
    UUID id;
    String username;
}
