package fr.ulco.dealhunter.models.mappers;

import fr.ulco.dealhunter.models.dto.CreateDealDto;
import fr.ulco.dealhunter.models.dto.UpdateDealDto;
import fr.ulco.dealhunter.models.entities.DealEntity;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link DealEntity} and its DTO {@link CreateDealDto} and {@link UpdateDealDto}.
 */
@Mapper(componentModel = "spring")
public interface DealMapper {
    DealEntity toEntity(CreateDealDto dto);
    DealEntity toEntity(UpdateDealDto dto);
}
