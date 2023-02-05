package fr.ulco.dealhunter.models.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Setter @Getter @NoArgsConstructor
public class DealEntity extends BaseEntity {
    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "isActive is required")
    private boolean isActive;
}
