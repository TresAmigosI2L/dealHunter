package fr.ulco.dealhunter.controllers;

import fr.ulco.dealhunter.models.dto.CreateDealRequestDto;
import fr.ulco.dealhunter.models.dto.DealResponseDto;
import fr.ulco.dealhunter.models.dto.UpdateDealRequestDto;
import fr.ulco.dealhunter.services.DealService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/deals")
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;

    @GetMapping
    public ResponseEntity<List<DealResponseDto>> getDeals() {
        List<DealResponseDto> dealsList = dealService.getAll();
        return ResponseEntity.ok(dealsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealResponseDto> getDeal(@PathVariable("id") UUID uuid) {
        try {
            DealResponseDto deal = dealService.get(uuid);
            return ResponseEntity.ok(deal);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DealResponseDto> createDeal(@Valid @RequestBody CreateDealRequestDto deal) {
        DealResponseDto createdDeal = dealService.create(deal);

        URI createdDealLocation = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdDeal.getId()).toUri();
        return ResponseEntity.created(createdDealLocation).body(createdDeal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealResponseDto> updateDeal(@PathVariable("id") UUID uuid, @Valid @RequestBody UpdateDealRequestDto deal) {
        try {
            DealResponseDto updatedDeal = dealService.update(uuid, deal);
            return ResponseEntity.ok(updatedDeal);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeal(@PathVariable("id") UUID uuid) {
        try {
            dealService.delete(uuid);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
