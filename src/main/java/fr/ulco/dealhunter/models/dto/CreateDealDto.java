package fr.ulco.dealhunter.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link fr.ulco.dealhunter.models.entities.DealEntity} entity
 */
@Data @NoArgsConstructor
public class CreateDealDto implements Serializable {
    private String title;
    private boolean isActive;
}