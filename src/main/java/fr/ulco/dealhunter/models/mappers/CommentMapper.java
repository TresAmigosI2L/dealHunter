package fr.ulco.dealhunter.models.mappers;

import fr.ulco.dealhunter.models.dto.deal.CommentDealRequestDto;
import fr.ulco.dealhunter.models.dto.deal.DealResponseDto;
import fr.ulco.dealhunter.models.entities.CommentEntity;
import fr.ulco.dealhunter.models.entities.DealEntity;
import org.mapstruct.Mapper;


/**
 * Mapper for the entity {@link fr.ulco.dealhunter.models.entities.CommentEntity} and its DTO {@link fr.ulco.dealhunter.models.dto.deal.CommentDealRequestDto}.
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentEntity toEntity(CommentDealRequestDto dto);

    CommentDealRequestDto toDto(CommentEntity entity);

}
