package fr.ulco.dealhunter.controllers;

import fr.ulco.dealhunter.models.dto.deal.*;
import fr.ulco.dealhunter.services.DealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{uuid}")
    public ResponseEntity<DealResponseDto> getDeal(@PathVariable UUID uuid) {
        DealResponseDto deal = dealService.get(uuid);
        return ResponseEntity.ok(deal);
    }

    @PostMapping
    public ResponseEntity<DealResponseDto> createDeal(@Valid @RequestBody CreateDealRequestDto deal) {
        DealResponseDto createdDeal = dealService.create(deal);
        URI createdDealLocation = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdDeal.getId()).toUri();
        return ResponseEntity.created(createdDealLocation).body(createdDeal);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<DealResponseDto> updateDeal(@PathVariable UUID uuid, @Valid @RequestBody UpdateDealRequestDto deal) {
        DealResponseDto updatedDeal = dealService.updateComment(uuid, deal);
        return ResponseEntity.ok(updatedDeal);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteDeal(@PathVariable UUID uuid) {
        try {
            dealService.delete(uuid);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{uuid}/upvote")
    public ResponseEntity<DealResponseDto> upVoteDeal(@PathVariable UUID uuid) {
        DealResponseDto deal = dealService.voteDeal(uuid, 1);
        return ResponseEntity.ok(deal);
    }

    @PutMapping("/{uuid}/downvote")
    public ResponseEntity<DealResponseDto> downVoteDeal(@PathVariable UUID uuid) {
        DealResponseDto deal = dealService.voteDeal(uuid, -1);
        return ResponseEntity.ok(deal);
    }

    @GetMapping("/{uuid}/degree")
    public ResponseEntity<Integer> getDegree(@PathVariable UUID uuid) {
        return ResponseEntity.ok(dealService.getDegreeOfDeal(uuid));
    }

    @PostMapping("/{uuid}/comment")
    public ResponseEntity<AddCommentDealRequestDto> addComment(@PathVariable UUID uuid, @Valid @RequestBody AddCommentDealRequestDto commentDealRequest) {
        try {
            AddCommentDealRequestDto addCommentDealRequestDto = dealService.addComment(uuid, commentDealRequest);
            return ResponseEntity.ok(addCommentDealRequestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{uuid}/comment")
    public ResponseEntity<AddCommentDealRequestDto> updateComment(@PathVariable UUID uuid, @Valid @RequestBody UpdateCommentDealRequestDto commentDealRequest) {
        try {
            AddCommentDealRequestDto addCommentDealRequestDto = dealService.updateComment(uuid, commentDealRequest);
            return ResponseEntity.ok(addCommentDealRequestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
