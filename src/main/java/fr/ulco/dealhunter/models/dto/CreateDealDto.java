package fr.ulco.dealhunter.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link fr.ulco.dealhunter.models.entities.DealEntity} entity
 */
@Data
public class CreateDealDto implements Serializable {
    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "isActive is required")
    private boolean isActive;
}