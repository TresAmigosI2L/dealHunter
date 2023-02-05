package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.models.dto.CreateDealDto;
import fr.ulco.dealhunter.models.dto.UpdateDealDto;
import fr.ulco.dealhunter.models.entities.DealEntity;
import fr.ulco.dealhunter.models.mappers.DealMapper;
import fr.ulco.dealhunter.repositories.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final DealMapper dealMapper;

    @Override
    public DealEntity create(CreateDealDto deal) {
        DealEntity dealEntity = dealMapper.toEntity(deal);
        return dealRepository.save(dealEntity);
    }

    @Override
    public DealEntity update(UpdateDealDto deal) {
        DealEntity dealEntity = dealMapper.toEntity(deal);
        return dealRepository.save(dealEntity);
    }

    @Override
    public DealEntity get(UUID id) {
        return dealRepository.findById(id).orElse(null);
    }

    @Override
    public List<DealEntity> getAll() {
        return dealRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        dealRepository.deleteById(id);
    }
}
