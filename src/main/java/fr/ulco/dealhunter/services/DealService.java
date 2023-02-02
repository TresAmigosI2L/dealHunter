package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.models.dto.DealRequest;
import fr.ulco.dealhunter.models.entities.DealEntity;
import fr.ulco.dealhunter.models.repositories.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealService {
    private final DealRepository dealRepository;

    public DealService(@Autowired DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    public void createDeal(DealRequest dealRequest) {
        DealEntity dealEntity = new DealEntity();
        dealEntity.setTitle(dealRequest.getTitle());
        dealEntity.setDescription(dealRequest.getDescription());
        dealEntity.setOriginalPrice(dealRequest.getOriginalPrice());
        dealEntity.setDiscountedPrice(dealRequest.getDiscountedPrice());
        dealEntity.setUrl(dealRequest.getUrl());
        dealEntity.setImageUrl(dealRequest.getImageUrl());
        dealRepository.save(dealEntity);
    }
}
