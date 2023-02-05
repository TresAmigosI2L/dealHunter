package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.models.dto.CreateDealRequestDto;
import fr.ulco.dealhunter.models.dto.DealResponseDto;
import fr.ulco.dealhunter.models.dto.UpdateDealRequestDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public interface DealService {
    DealResponseDto create(CreateDealRequestDto deal);
    DealResponseDto update(UUID uuid, UpdateDealRequestDto deal);
    DealResponseDto get(UUID id) throws EntityNotFoundException;
    List<DealResponseDto> getAll();
    void delete(UUID id);
}
