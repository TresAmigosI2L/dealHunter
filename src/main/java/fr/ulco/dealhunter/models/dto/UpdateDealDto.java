package fr.ulco.dealhunter.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link fr.ulco.dealhunter.models.entities.DealEntity} entity
 */
@Data
public class UpdateDealDto implements Serializable {
    @NotNull(message = "Id is required")
    private UUID id;
    private String title;
    private boolean isActive;
}