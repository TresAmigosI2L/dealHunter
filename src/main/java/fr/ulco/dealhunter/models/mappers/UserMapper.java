package fr.ulco.dealhunter.models.mappers;

import fr.ulco.dealhunter.models.dto.auth.CreateUserRequestDto;
import fr.ulco.dealhunter.models.dto.auth.UserResponseDto;
import fr.ulco.dealhunter.models.entities.RoleEntity;
import fr.ulco.dealhunter.models.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "authorities", target = "authorities", qualifiedByName = "stringToRole")
    UserEntity toEntity(CreateUserRequestDto dto);

    UserResponseDto toOutputDto(UserEntity entity);

    @Named("stringToRole")
    default Set<RoleEntity> stringToRole(Set<String> authorities) {
        if (authorities != null) {
            return authorities.stream().map(RoleEntity::new).collect(toSet());
        }
        return new HashSet<>();
    }
}
