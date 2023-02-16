package fr.ulco.dealhunter.models.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO to update a {@link fr.ulco.dealhunter.models.entities.DealEntity} entity
 */
@Data
public class UpdateDealRequestDto implements Serializable {
    private String title;
    private boolean isActive;
}