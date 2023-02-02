package fr.ulco.dealhunter.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="deals")
public class DealEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private UserEntity author;
    @Column(name="title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "original_price")
    private Double originalPrice;
    @Column(name = "discounted_price")
    private Double discountedPrice;
    @Column(name="url")
    private String url;
    @Column(name="image_url")
    private String imageUrl;
    @Basic
    @Column(name = "created_at", updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;
    @Basic
    @Column(name = "updated_at", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime updatedAt;
}

