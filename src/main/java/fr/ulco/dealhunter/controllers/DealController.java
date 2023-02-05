package fr.ulco.dealhunter.controllers;

import fr.ulco.dealhunter.models.entities.DealEntity;
import fr.ulco.dealhunter.services.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/deals")
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;

    @GetMapping
    public List<DealEntity> getDeals() {
        return dealService.getAll();
    }
}
