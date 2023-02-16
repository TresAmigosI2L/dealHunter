package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.models.dto.CreateDealRequestDto;
import fr.ulco.dealhunter.models.dto.DealResponseDto;
import fr.ulco.dealhunter.models.dto.UpdateDealRequestDto;
import fr.ulco.dealhunter.models.entities.DealEntity;
import fr.ulco.dealhunter.models.mappers.DealMapper;
import fr.ulco.dealhunter.repositories.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class DealService {
    private final DealRepository dealRepository;
    private final DealMapper dealMapper;

    public DealResponseDto create(CreateDealRequestDto deal) {
        DealEntity dealEntity = dealMapper.toEntity(deal);
        dealRepository.save(dealEntity);
        return dealMapper.toDto(dealEntity);
    }

    public DealResponseDto update(UUID uuid, UpdateDealRequestDto deal) throws ResponseStatusException {
        DealEntity dealEntity = dealRepository.findById(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Deal to update not found"));
        dealMapper.updateEntity(deal, dealEntity);
        dealRepository.save(dealEntity);
        return dealMapper.toDto(dealEntity);
    }

    public DealResponseDto get(UUID id) throws ResponseStatusException {
        DealEntity dealEntity = dealRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Deal not found"));
        return dealMapper.toDto(dealEntity);
    }

    public List<DealResponseDto> getAll() {
        List<DealEntity> dealEntityList = dealRepository.findAll();
        return dealEntityList.stream().map(dealMapper::toDto).toList();
    }

    public void delete(UUID id) throws ResponseStatusException {
        try {
            dealRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deal to delete not found");
        }
    }
}
