package ru.job4j.accident.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.accident.config.SpringMapperConfig;
import ru.job4j.accident.dto.UserDto;
import ru.job4j.accident.model.User;

@Mapper(config = SpringMapperConfig.class)
public interface UserMapper {

    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.password", target = "password")
    @Mapping(source = "dto.username", target = "username")
    @Mapping(source = "dto.authority", target = "authority")
    @Mapping(source = "dto.enabled", target = "enabled")
    User map(UserDto dto);

}
