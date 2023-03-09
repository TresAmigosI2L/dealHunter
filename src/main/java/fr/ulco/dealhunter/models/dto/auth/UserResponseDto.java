package fr.ulco.dealhunter.models.dto.auth;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A DTO to output a {@link fr.ulco.dealhunter.models.entities.UserEntity} entity
 */
@Data
public class UserResponseDto implements Serializable {
    UUID id;
    String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
