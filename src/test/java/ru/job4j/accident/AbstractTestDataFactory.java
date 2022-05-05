package ru.job4j.accident;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractTestDataFactory {

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    public <T> T getResource(String path, TypeReference<T> typeReference) {
        return objectMapper.readValue(getClass().getResource(path), typeReference);
    }

}