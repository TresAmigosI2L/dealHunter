package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.models.dto.DealRequest;
import fr.ulco.dealhunter.models.entities.DealEntity;
import fr.ulco.dealhunter.models.repositories.DealRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;

    @Override
    public DealEntity createDeal(DealRequest dealRequest) {

        DealEntity dealEntity = new DealEntity();
        dealEntity.setTitle(dealRequest.getTitle());
        dealEntity.setUrl(dealRequest.getUrl());

        return dealRepository.save(dealEntity);
    }
}
