package ru.job4j.accident.integration.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.accident.annotation.IntegrationTest;
import ru.job4j.accident.service.AccidentRuleService;

@IntegrationTest
class AccidentRuleServiceIntegrationTest {

    @Autowired
    private AccidentRuleService accidentRuleService;

    @Test
    void findAccidentRuleById() {
        var actual = accidentRuleService.findAccidentRuleById(1);
        var expectedName = "Статья-1";
        Assertions.assertEquals(expectedName, actual.getName());
    }

    @Test
    void findAllAccidentRules() {
        var actual = accidentRuleService.findAllAccidentRules();
        var expectedName = "Статья-1";
        Assertions.assertEquals(3, actual.size());
        Assertions.assertEquals(expectedName, actual.get(0).getName());
    }

}