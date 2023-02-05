package fr.ulco.dealhunter.models.mappers;

import fr.ulco.dealhunter.models.dto.CreateDealRequestDto;
import fr.ulco.dealhunter.models.dto.DealResponseDto;
import fr.ulco.dealhunter.models.dto.UpdateDealRequestDto;
import fr.ulco.dealhunter.models.entities.DealEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapper for the entity {@link DealEntity} and its DTO {@link CreateDealRequestDto} and {@link UpdateDealRequestDto}.
 */
@Mapper(componentModel = "spring")
public interface DealMapper {
    DealEntity toEntity(CreateDealRequestDto dto);
    DealResponseDto toDto(DealEntity entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdateDealRequestDto dto, @MappingTarget DealEntity entity);

}
