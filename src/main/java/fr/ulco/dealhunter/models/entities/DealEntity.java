package fr.ulco.dealhunter.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "deals")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DealEntity extends BaseEntity {
    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "Active is required")
    private boolean active;
    private String author;
    private int votes;
}
