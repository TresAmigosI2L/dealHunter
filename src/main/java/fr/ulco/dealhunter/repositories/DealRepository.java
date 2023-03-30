package fr.ulco.dealhunter.repositories;

import fr.ulco.dealhunter.models.entities.DealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface DealRepository extends JpaRepository<DealEntity, UUID> {
    @Query("SELECT SUM(d.votes) FROM DealEntity d")
    Integer getTotalVotes();
}