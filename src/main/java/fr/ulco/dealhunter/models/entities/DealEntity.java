package fr.ulco.dealhunter.models.entities;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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
    private String imageUrl;
    private Double originalPrice;
    private Double discountPrice;
    private String dealUrl;

    @ElementCollection
    private Set<CommentEntity> comments = new HashSet<>();
}
