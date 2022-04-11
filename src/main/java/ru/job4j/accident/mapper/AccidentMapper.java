package ru.job4j.accident.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.accident.config.SpringMapperConfig;
import ru.job4j.accident.dto.AccidentDto;
import ru.job4j.accident.model.Accident;

@Mapper(config = SpringMapperConfig.class)
public interface AccidentMapper {

    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "dto.text", target = "text")
    @Mapping(source = "dto.address", target = "address")
    @Mapping(source = "dto.accidentType", target = "accidentType")
    @Mapping(source = "dto.rules", target = "rules")
    Accident map(AccidentDto dto);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.name", target = "name")
    @Mapping(source = "entity.text", target = "text")
    @Mapping(source = "entity.address", target = "address")
    @Mapping(source = "entity.accidentType", target = "accidentType")
    @Mapping(source = "entity.rules", target = "rules")
    AccidentDto map(Accident entity);

}
