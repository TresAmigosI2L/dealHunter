package fr.ulco.dealhunter.models.mappers;

import fr.ulco.dealhunter.models.dto.DealRequest;
import fr.ulco.dealhunter.models.entities.DealEntity;
import org.mapstruct.Mapper;


@Mapper
public interface DealMapper {
    DealEntity toDealEntity(DealRequest dealRequest);
    DealRequest toDealRequest(DealEntity dealEntity );
}
