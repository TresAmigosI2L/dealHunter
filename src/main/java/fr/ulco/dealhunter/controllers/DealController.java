package fr.ulco.dealhunter.controllers;

import fr.ulco.dealhunter.models.dto.CreateDealDto;
import fr.ulco.dealhunter.models.dto.UpdateDealDto;
import fr.ulco.dealhunter.models.entities.DealEntity;
import fr.ulco.dealhunter.services.DealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/deals")
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;

    @GetMapping
    public ResponseEntity<List<DealEntity>> getDeals() {
        List<DealEntity> dealsList = dealService.getAll();
        if (dealsList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dealsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealEntity> getDeal(@PathVariable("id") UUID uuid) {
        DealEntity deal = dealService.get(uuid);
        if (deal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deal);
    }

    @PostMapping
    public ResponseEntity<DealEntity> createDeal(@Valid @RequestBody CreateDealDto deal) {
        DealEntity createdDeal = dealService.create(deal);
        return ResponseEntity.created(null).body(createdDeal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealEntity> updateDeal(@PathVariable("id") UUID uuid, @Valid @RequestBody UpdateDealDto deal) {
        DealEntity updatedDeal = dealService.update(deal);
        return ResponseEntity.ok(updatedDeal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeal(@PathVariable("id") UUID uuid) {
        dealService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
