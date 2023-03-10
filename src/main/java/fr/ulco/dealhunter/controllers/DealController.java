package fr.ulco.dealhunter.controllers;

import fr.ulco.dealhunter.models.dto.deal.CreateDealRequestDto;
import fr.ulco.dealhunter.models.dto.deal.DealResponseDto;
import fr.ulco.dealhunter.models.dto.deal.UpdateDealRequestDto;
import fr.ulco.dealhunter.services.DealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
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
        Optional<DealResponseDto> deal = dealService.get(uuid);
        return deal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
        return dealService.update(uuid, deal)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeal(@PathVariable("id") UUID uuid) {
        try {
            dealService.delete(uuid);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
