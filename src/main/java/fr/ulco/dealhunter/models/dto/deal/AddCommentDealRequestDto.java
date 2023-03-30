package fr.ulco.dealhunter.models.dto.deal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A DTO to create a {@link fr.ulco.dealhunter.models.entities.CommentEntity} entity
 */
@Data
public class AddCommentDealRequestDto implements Serializable {
    private String author;

    @NotNull
    private String message;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID id;

}