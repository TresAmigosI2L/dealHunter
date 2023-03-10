package fr.ulco.dealhunter.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "deals")
@Setter
@Getter
@NoArgsConstructor
public class DealEntity extends BaseEntity {
    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "Active is required")
    private boolean active;
    private String author;
    private int votes;
    private double degrees;

    public DealEntity(UUID randomUUID, String s, String s1, int i) {
        super();
    }
}
