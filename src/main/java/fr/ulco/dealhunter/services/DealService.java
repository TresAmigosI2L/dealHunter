package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.models.dto.DealRequest;
import fr.ulco.dealhunter.models.entities.DealEntity;
import fr.ulco.dealhunter.models.mappers.DealMapper;
import fr.ulco.dealhunter.models.repositories.DealRepository;
import org.springframework.stereotype.Service;

@Service
public class DealService {

    private DealRepository dealRepository;
    private DealMapper dealMapper;
    public void createDeal(DealRequest dealRequest) {
        DealEntity dealEntity = dealMapper.toDealEntity(dealRequest);
        dealRepository.save(dealEntity);
    }
}
