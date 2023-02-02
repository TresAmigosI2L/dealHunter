package fr.ulco.dealhunter.models.mappers;

import fr.ulco.dealhunter.models.dto.DealRequest;
import org.springframework.context.annotation.Bean;

@Bean
public interface DealMapper {
    DealRequest toDealEntity(DealRequest dealRequest);
}
