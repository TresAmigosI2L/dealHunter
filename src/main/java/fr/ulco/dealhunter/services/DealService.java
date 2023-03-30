package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.models.dto.deal.CommentDealRequestDto;
import fr.ulco.dealhunter.models.dto.deal.CreateDealRequestDto;
import fr.ulco.dealhunter.models.dto.deal.DealResponseDto;
import fr.ulco.dealhunter.models.dto.deal.UpdateDealRequestDto;
import fr.ulco.dealhunter.models.entities.CommentEntity;
import fr.ulco.dealhunter.models.entities.DealEntity;
import fr.ulco.dealhunter.models.mappers.CommentMapper;
import fr.ulco.dealhunter.models.mappers.DealMapper;
import fr.ulco.dealhunter.repositories.CommentRepository;
import fr.ulco.dealhunter.repositories.DealRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DealService {
    private final DealRepository dealRepository;
    private final CommentRepository commentRepository;
    private final DealMapper dealMapper;
    private final CommentMapper commentMapper;
    private final AuthService authService;

    public DealResponseDto create(CreateDealRequestDto deal) {
        DealEntity dealEntity = dealMapper.toEntity(deal);
        dealEntity.setAuthor(authService.getUsernameOfAuthenticatedUser());
        if (dealEntity.getImageUrl() == null){
            dealEntity.setImageUrl("https://caer.univ-amu.fr/wp-content/uploads/default-placeholder.png");
        }
        dealRepository.save(dealEntity);
        return dealMapper.toDto(dealEntity);
    }

    public Optional<DealResponseDto> update(UUID uuid, UpdateDealRequestDto newDeal) {
        return dealRepository.findById(uuid).map(dealEntity -> {
            dealMapper.updateEntity(newDeal, dealEntity);
            dealRepository.save(dealEntity);
            return dealMapper.toDto(dealEntity);
        });
    }

    public Optional<DealResponseDto> get(UUID id) {
        return dealRepository.findById(id)
                .map(dealMapper::toDto);
    }

    public List<DealResponseDto> getAll() {
        List<DealEntity> dealEntityList = dealRepository.findAll();
        return dealEntityList.stream().map(dealMapper::toDto).toList();
    }

    public void delete(UUID id) throws EmptyResultDataAccessException {
        dealRepository.deleteById(id);
    }

    @Transactional
    public DealResponseDto voteDeal(UUID uuid, int voteDirection) {
        Optional<DealEntity> dealOpt = dealRepository.findById(uuid);
        if (dealOpt.isPresent()) {
            DealEntity deal = dealOpt.get();
            deal.setVotes(deal.getVotes() + voteDirection);
            dealRepository.save(deal);
            return dealMapper.toDto(deal);
        } else {
            throw new IllegalArgumentException("Deal not found with UUID: " + uuid);
        }
    }

    public Integer getDegreeOfDeal(UUID id) {
        Optional<DealEntity> dealOpt = dealRepository.findById(id);
        if (dealOpt.isPresent()) {
            DealEntity deal = dealOpt.get();
            return deal.getVotes();
        } else {
            throw new IllegalArgumentException("Deal not found with UUID: " + id);
        }
    }

    @Transactional
    public CommentDealRequestDto addComment(UUID id, CommentDealRequestDto commentDealRequestDto){
        Optional<DealEntity> dealOpt = dealRepository.findById(id);
        if (dealOpt.isPresent()) {
            DealEntity deal = dealOpt.get();
            var comments = deal.getComments();
            CommentEntity comment = commentMapper.toEntity(commentDealRequestDto);
            comment.setAuthor(authService.getUsernameOfAuthenticatedUser());
            comments.add(comment);
            deal.setComments(comments);
            commentRepository.save(comment);
            dealRepository.save(deal);
            return commentMapper.toDto(comment);
        } else {
            throw new IllegalArgumentException("Deal not found with UUID: " + id);
        }
    }

}
