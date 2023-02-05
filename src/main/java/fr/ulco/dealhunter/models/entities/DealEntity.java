package fr.ulco.dealhunter.models.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Setter @Getter @NoArgsConstructor
public class DealEntity extends BaseEntity {
    private String title;
    private boolean isActive;
}
