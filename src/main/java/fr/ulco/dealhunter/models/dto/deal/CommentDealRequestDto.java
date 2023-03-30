package fr.ulco.dealhunter.models.dto.deal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

/**
 * A DTO to create a {@link fr.ulco.dealhunter.models.entities.CommentEntity} entity
 */
@Data
public class CommentDealRequestDto implements Serializable {
    private String author;

    @NotNull
    private String message;
}