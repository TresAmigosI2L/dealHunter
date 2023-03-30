package fr.ulco.dealhunter.models.mappers;

import fr.ulco.dealhunter.models.dto.deal.CommentDealRequestDto;
import fr.ulco.dealhunter.models.dto.deal.UpdateCommentDealRequestDto;
import fr.ulco.dealhunter.models.entities.CommentEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


/**
 * Mapper for the entity {@link fr.ulco.dealhunter.models.entities.CommentEntity} and its DTO {@link fr.ulco.dealhunter.models.dto.deal.CommentDealRequestDto}.
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentEntity toEntity(CommentDealRequestDto dto);

    CommentDealRequestDto toDto(CommentEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdateCommentDealRequestDto dto, @MappingTarget CommentEntity entity);

}
