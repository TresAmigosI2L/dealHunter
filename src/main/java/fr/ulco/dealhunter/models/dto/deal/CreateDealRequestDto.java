package fr.ulco.dealhunter.models.dto.deal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO to create a {@link fr.ulco.dealhunter.models.entities.DealEntity} entity
 */
@Data
public class CreateDealRequestDto implements Serializable {
    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "active is required")
    private boolean active;
    private String imageUrl;
}