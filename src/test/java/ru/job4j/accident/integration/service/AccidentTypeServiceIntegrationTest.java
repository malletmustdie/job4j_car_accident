package ru.job4j.accident.integration.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.accident.annotation.IntegrationTest;
import ru.job4j.accident.service.AccidentTypeService;

@IntegrationTest
class AccidentTypeServiceIntegrationTest {

    @Autowired
    private AccidentTypeService accidentTypeService;

    @Test
    void findAccidentTypeById() {
        var expectedName = "Две машины";
        var actual = accidentTypeService.findAccidentTypeById(1);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedName, actual.getName());
    }

    @Test
    void findAllAccidentTypes() {
        var expectedName = "Две машины";
        var actual = accidentTypeService.findAllAccidentTypes();
        Assertions.assertEquals(3, actual.size());
        Assertions.assertEquals(expectedName, actual.get(0).getName());
    }

}