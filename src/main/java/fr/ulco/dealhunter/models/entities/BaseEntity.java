package fr.ulco.dealhunter.models.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Base entity for all entities (include an ID and timestamps)
 */
@MappedSuperclass
@Setter
@Getter
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    protected UUID id;

    @CreationTimestamp
    @GeneratedValue
    protected LocalDateTime createdAt;
    @UpdateTimestamp
    @GeneratedValue
    protected LocalDateTime updatedAt;
}
