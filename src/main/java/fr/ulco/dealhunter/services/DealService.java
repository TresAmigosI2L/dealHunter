package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.exceptions.DealNotFoundException;
import fr.ulco.dealhunter.models.dto.deal.*;
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

    public DealResponseDto updateComment(UUID uuid, UpdateDealRequestDto newDeal) {
        return dealRepository.findById(uuid).map(dealEntity -> {
            dealMapper.updateEntity(newDeal, dealEntity);
            dealRepository.save(dealEntity);
            return dealMapper.toDto(dealEntity);
        }).orElseThrow(() -> new DealNotFoundException(uuid));
    }

    public DealResponseDto get(UUID uuid) {
        return dealRepository.findById(uuid)
                .map(dealMapper::toDto)
                .orElseThrow(() -> new DealNotFoundException(uuid));
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
        return dealRepository.findById(uuid).map(dealEntity -> {
            dealEntity.setVotes(dealEntity.getVotes() + voteDirection);
            dealRepository.save(dealEntity);
            return dealMapper.toDto(dealEntity);
        }).orElseThrow(() -> new DealNotFoundException(uuid));
    }

    public Integer getDegreeOfDeal(UUID uuid) {
        return dealRepository.findById(uuid).map(DealEntity::getVotes).orElseThrow(() -> new DealNotFoundException(uuid));
    }

    @Transactional
    public AddCommentDealRequestDto addComment(UUID uuid, AddCommentDealRequestDto addCommentDealRequestDto){
        return dealRepository.findById(uuid).map(dealEntity -> {
            CommentEntity commentEntity = commentMapper.toEntity(addCommentDealRequestDto);
            commentEntity.setAuthor(authService.getUsernameOfAuthenticatedUser());

            dealEntity.getComments().add(commentEntity);
            commentRepository.save(commentEntity);

            commentRepository.save(commentEntity);
            dealRepository.save(dealEntity);

            return commentMapper.toDto(commentEntity);
        }).orElseThrow(() -> new DealNotFoundException(uuid));
    }

    public AddCommentDealRequestDto updateComment(UUID uuid, UpdateCommentDealRequestDto updateCommentDealRequestDto) {
        return commentRepository.findById(uuid).map(commentEntity -> {
            commentMapper.updateEntity(updateCommentDealRequestDto, commentEntity);
            commentRepository.save(commentEntity);
            return commentMapper.toDto(commentEntity);
        }).orElseThrow(() -> new DealNotFoundException(uuid));
    }
}
