package fr.ulco.dealhunter.repositories;

import fr.ulco.dealhunter.models.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
}