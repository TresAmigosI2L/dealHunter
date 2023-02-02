package fr.ulco.dealhunter.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name="deals")
public class DealEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private UserEntity author;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "original_price", nullable = false)
    private Double originalPrice;
    @Column(name = "discounted_price", nullable = false)
    private Double discountedPrice;
    @Column(name="url", nullable = false)
    private String url;
    @Column(name="image_url", nullable = false)
    private String imageUrl;
    @Basic
    @Column(name = "created_at", updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;
    @Basic
    @Column(name = "updated_at", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime updatedAt;
}
