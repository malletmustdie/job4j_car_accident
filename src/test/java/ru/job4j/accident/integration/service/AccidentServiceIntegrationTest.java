package ru.job4j.accident.integration.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.accident.TestDataFactory;
import ru.job4j.accident.annotation.IntegrationTest;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.service.AccidentService;

@IntegrationTest
class AccidentServiceIntegrationTest {

    @Autowired
    private TestDataFactory dataFactory;

    @Autowired
    private AccidentService accidentService;

    @Autowired
    private AccidentRepository accidentRepository;

    @BeforeEach
    public void setUp() {
        accidentRepository.deleteAll();
    }

    @Test
    void whenSaveNewAccident() {
        var expected = dataFactory.newAccidentDto();
        var rulesId = new String[] {"1", "2", "3"};
        accidentService.addAccident(expected, rulesId);
        var actual = accidentRepository.findByName(expected.getName());
        Assertions.assertTrue(actual.isPresent());
        actual.ifPresent(actualAccident -> {
            Assertions.assertEquals(expected.getName(), actualAccident.getName());
            Assertions.assertEquals(expected.getAddress(), actualAccident.getAddress());
            Assertions.assertEquals(expected.getText(), actualAccident.getText());
            Assertions.assertEquals(3, actualAccident.getRules().size());
        });
    }

    @Test
    void whenUpdateAccident() {
        var dto = dataFactory.newAccidentDto();
        var rulesId = new String[] {"1", "2", "3"};
        accidentService.addAccident(dto, rulesId);
        var acc = accidentRepository.findByName(dto.getName());
        Assertions.assertTrue(acc.isPresent());
        var expected = dataFactory.updatedAccidentDto();
        accidentService.updateAccident(expected);
        var actual = accidentRepository.findByName(expected.getName());
        actual.ifPresent(actualAccident -> {
            Assertions.assertEquals(expected.getName(), actualAccident.getName());
            Assertions.assertEquals(expected.getAddress(), actualAccident.getAddress());
            Assertions.assertEquals(expected.getText(), actualAccident.getText());
        });
    }

    @Test
    void whenSaveAccidentAndGetAll() {
        var expected = dataFactory.newAccidentDto();
        var rulesId = new String[] {"1", "2", "3"};
        accidentService.addAccident(expected, rulesId);
        var actual = accidentService.findAllAccidents();
        Assertions.assertEquals(1, actual.size());
    }

}
