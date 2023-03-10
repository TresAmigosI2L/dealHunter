package fr.ulco.dealhunter.models.dto.deal;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A DTO to represent a {@link fr.ulco.dealhunter.models.entities.DealEntity} entity
 */
@Data
public class DealResponseDto implements Serializable {
    private UUID id;
    private String title;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String author;
    private int votes;
    private int degrees;
}
