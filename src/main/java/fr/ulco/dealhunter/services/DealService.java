package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.models.dto.deal.CreateDealRequestDto;
import fr.ulco.dealhunter.models.dto.deal.DealResponseDto;
import fr.ulco.dealhunter.models.dto.deal.UpdateDealRequestDto;
import fr.ulco.dealhunter.models.entities.DealEntity;
import fr.ulco.dealhunter.models.mappers.DealMapper;
import fr.ulco.dealhunter.repositories.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DealService {
    private final DealRepository dealRepository;
    private final DealMapper dealMapper;
    private final AuthService authService;

    public DealResponseDto create(CreateDealRequestDto deal) {
        DealEntity dealEntity = dealMapper.toEntity(deal);
        dealEntity.setAuthor(authService.getUsernameOfAuthenticatedUser());
        dealRepository.save(dealEntity);
        return dealMapper.toDto(dealEntity);
    }

    public DealResponseDto update(UUID uuid, UpdateDealRequestDto newDeal) {
        DealEntity dealEntity = dealRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Deal not found with UUID: " + uuid));
        dealMapper.updateEntity(newDeal, dealEntity);
        dealRepository.save(dealEntity);
        return dealMapper.toDto(dealEntity);
    }

    public DealResponseDto get(UUID uuid) {
        DealEntity dealEntity = dealRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Deal not found with UUID: " + uuid));
        return dealMapper.toDto(dealEntity);
    }

    public List<DealResponseDto> getAll() {
        List<DealEntity> dealEntityList = dealRepository.findAll();
        return dealEntityList.stream().map(dealMapper::toDto).toList();
    }

    public void delete(UUID uuid) throws EmptyResultDataAccessException {
        dealRepository.deleteById(uuid);
    }
}
