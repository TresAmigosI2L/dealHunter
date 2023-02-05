package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.models.dto.CreateDealRequestDto;
import fr.ulco.dealhunter.models.dto.DealResponseDto;
import fr.ulco.dealhunter.models.dto.UpdateDealRequestDto;
import fr.ulco.dealhunter.models.entities.DealEntity;
import fr.ulco.dealhunter.models.mappers.DealMapper;
import fr.ulco.dealhunter.repositories.DealRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final DealMapper dealMapper;

    @Override
    public DealResponseDto create(CreateDealRequestDto deal) {
        DealEntity dealEntity = dealMapper.toEntity(deal);
        dealRepository.save(dealEntity);
        return dealMapper.toDto(dealEntity);
    }

    @Override
    public DealResponseDto update(UUID uuid, UpdateDealRequestDto deal) throws EntityNotFoundException {
        DealEntity dealEntity = dealRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("Deal to update not found"));
        dealMapper.updateEntity(deal, dealEntity);
        dealRepository.save(dealEntity);
        return dealMapper.toDto(dealEntity);
    }

    @Override
    public DealResponseDto get(UUID id) throws EntityNotFoundException {
        DealEntity dealEntity = dealRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Deal not found"));
        return dealMapper.toDto(dealEntity);
    }

    @Override
    public List<DealResponseDto> getAll() {
        List<DealEntity> dealEntityList = dealRepository.findAll();
        return dealEntityList.stream().map(dealMapper::toDto).toList();
    }

    @Override
    public void delete(UUID id) throws EntityNotFoundException {
        try {
            dealRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Deal to delete not found");
        }
    }
}
