package fr.ulco.dealhunter.controllers;

import fr.ulco.dealhunter.models.dto.DealRequest;
import fr.ulco.dealhunter.models.entities.DealEntity;
import fr.ulco.dealhunter.services.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/deals")
@RestController
public class DealController {
    @Autowired
    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DealEntity createDeal(@RequestBody DealRequest dealRequest) {
        return dealService.createDeal(dealRequest);
    }
}
