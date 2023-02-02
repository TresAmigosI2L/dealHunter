package fr.ulco.dealhunter.models.repositories;

import fr.ulco.dealhunter.models.entities.DealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<DealEntity, Long> {
}
