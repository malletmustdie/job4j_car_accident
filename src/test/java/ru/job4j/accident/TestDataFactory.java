package ru.job4j.accident;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.job4j.accident.dto.AccidentDto;
import ru.job4j.accident.dto.UserDto;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

@Component
public class TestDataFactory extends AbstractTestDataFactory {

    private static final String NEW_ACCIDENT_DTO_REFERENCE = "/data/json/new-accident.json";

    private static final String UPDATED_ACCIDENT_DTO_REFERENCE = "/data/json/updated-accident.json";

    private static final String RULE_REFERENCE = "/data/json/rule.json";

    private static final String TYPE_REFERENCE = "/data/json/type.json";

    private static final String USER_REFERENCE = "/data/json/user.json";

    @SneakyThrows
    public AccidentDto newAccidentDto() {
        return getResource(NEW_ACCIDENT_DTO_REFERENCE, new TypeReference<>() {
        });
    }

    @SneakyThrows
    public AccidentDto updatedAccidentDto() {
        return getResource(UPDATED_ACCIDENT_DTO_REFERENCE, new TypeReference<>() {
        });
    }

    @SneakyThrows
    public Rule newRule() {
        return getResource(RULE_REFERENCE, new TypeReference<>() {
        });
    }

    @SneakyThrows
    public AccidentType newType() {
        return getResource(TYPE_REFERENCE, new TypeReference<>() {
        });
    }

    @SneakyThrows
    public UserDto newUserDto() {
        return getResource(USER_REFERENCE, new TypeReference<>() {
        });
    }

}