package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.models.dto.DealRequest;
import fr.ulco.dealhunter.models.entities.DealEntity;

public interface DealService {
    DealEntity createDeal(DealRequest dealRequest);
}
