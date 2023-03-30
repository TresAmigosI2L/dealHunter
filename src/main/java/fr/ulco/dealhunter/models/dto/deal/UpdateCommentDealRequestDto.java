package fr.ulco.dealhunter.models.dto.deal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO to update a {@link fr.ulco.dealhunter.models.entities.CommentEntity} entity
 */
@Data
public class UpdateCommentDealRequestDto implements Serializable {
    private String author;
    @NotNull
    private String message;

}