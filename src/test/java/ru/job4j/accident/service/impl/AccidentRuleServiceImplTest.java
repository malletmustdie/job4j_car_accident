package ru.job4j.accident.service.impl;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.job4j.accident.TestDataFactory;
import ru.job4j.accident.annotation.UnitTest;
import ru.job4j.accident.repository.AccidentRuleRepository;
import ru.job4j.accident.service.AccidentRuleService;

@UnitTest
class AccidentRuleServiceImplTest {

    @Autowired
    private TestDataFactory dataFactory;

    @MockBean
    private AccidentRuleRepository accidentRuleRepository;

    @Autowired
    private AccidentRuleService accidentRuleService;

    @Test
    void findAccidentRuleById() {
        var expected = dataFactory.newRule();
        Mockito.when(accidentRuleRepository.findById(Mockito.anyInt()))
               .thenReturn(Optional.of(expected));
        var actual = accidentRuleService.findAccidentRuleById(1);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void findAllAccidentRules() {
        var mockRule = dataFactory.newRule();
        Mockito.when(accidentRuleRepository.findAll()).thenReturn(List.of(mockRule));
        var actual = accidentRuleService.findAllAccidentRules();
        Assertions.assertEquals(1, actual.size());
    }

}