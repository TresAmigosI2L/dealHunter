package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.models.dto.CreateDealDto;
import fr.ulco.dealhunter.models.dto.UpdateDealDto;
import fr.ulco.dealhunter.models.entities.DealEntity;

import java.util.List;
import java.util.UUID;

public interface DealService {
    DealEntity create(CreateDealDto deal);
    DealEntity update(UpdateDealDto deal);
    DealEntity get(UUID id);
    List<DealEntity> getAll();
    void delete(UUID id);
}
