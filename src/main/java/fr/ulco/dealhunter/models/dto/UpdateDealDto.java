package fr.ulco.dealhunter.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link fr.ulco.dealhunter.models.entities.DealEntity} entity
 */
@Data @NoArgsConstructor
public class UpdateDealDto implements Serializable {
    private UUID id;
    private String title;
    private boolean isActive;
}