package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.models.dto.DealRequest;
import fr.ulco.dealhunter.models.entities.DealEntity;
import fr.ulco.dealhunter.models.repositories.DealRepository;

public class DealService {

    private DealRepository dealRepository;
    public void createDeal(DealRequest dealRequest) {
        DealEntity dealEntity = new DealEntity();
        dealEntity.setTitle(dealRequest.getTitle());
        dealEntity.setDescription(dealRequest.getDescription());
        dealEntity.setUrl(dealRequest.getUrl());
        dealRepository.save(dealEntity);
    }
}
