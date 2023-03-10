package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.models.dto.auth.UserResponseDto;
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
import java.util.Optional;
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

    public Optional<DealResponseDto> update(UUID uuid, UpdateDealRequestDto newDeal) {
        return dealRepository.findById(uuid).map(dealEntity -> {
            dealMapper.updateEntity(newDeal, dealEntity);
            dealRepository.save(dealEntity);
            return dealMapper.toDto(dealEntity);
        });
    }

    public Optional<DealResponseDto> get(UUID id) {
        return dealRepository.findById(id)
                .map(dealMapper::toDto);
    }

    public List<DealResponseDto> getAll() {
        List<DealEntity> dealEntityList = dealRepository.findAll();
        return dealEntityList.stream().map(dealMapper::toDto).toList();
    }

    public void delete(UUID id) throws EmptyResultDataAccessException {
        dealRepository.deleteById(id);
    }
}
