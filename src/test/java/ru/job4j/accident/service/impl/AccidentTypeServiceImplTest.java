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
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.service.AccidentTypeService;

@UnitTest
class AccidentTypeServiceImplTest {

    @Autowired
    private TestDataFactory dataFactory;

    @MockBean
    private AccidentTypeRepository accidentTypeRepository;

    @Autowired
    private AccidentTypeService accidentTypeService;

    @Test
    void findAccidentTypeById() {
        var expected = dataFactory.newType();
        Mockito.when(accidentTypeRepository.findById(Mockito.anyInt()))
               .thenReturn(Optional.of(expected));
        var actual = accidentTypeService.findAccidentTypeById(1);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void findAllAccidentTypes() {
        var expected = dataFactory.newType();
        Mockito.when(accidentTypeRepository.findAll()).thenReturn(List.of(expected));
        var actual = accidentTypeService.findAllAccidentTypes();
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(expected.getName(), actual.get(0).getName());
    }

}