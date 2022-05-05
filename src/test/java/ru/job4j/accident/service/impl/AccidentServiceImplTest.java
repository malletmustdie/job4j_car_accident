package ru.job4j.accident.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.job4j.accident.TestDataFactory;
import ru.job4j.accident.annotation.UnitTest;
import ru.job4j.accident.mapper.AccidentMapper;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentRuleRepository;
import ru.job4j.accident.service.AccidentService;

@UnitTest
class AccidentServiceImplTest {

    @Autowired
    private TestDataFactory dataFactory;

    @MockBean
    private AccidentRepository accidentRepository;

    @MockBean
    private AccidentRuleRepository accidentRuleRepository;

    @MockBean
    private AccidentMapper accidentMapper;

    @Autowired
    private AccidentService accidentService;

    @Test
    void whenSaveNewAccident() {
        var dto = dataFactory.newAccidentDto();
        var mockRule = Optional.of(Rule.builder()
                                       .id(1)
                                       .name("asd")
                                       .build());
        var mockAccident = Accident.builder()
                                   .id(1)
                                   .name("name")
                                   .text("text")
                                   .address("address")
                                   .accidentType(AccidentType.builder()
                                                             .id(1)
                                                             .name("name")
                                                             .build())
                                   .build();
        Mockito.when(accidentMapper.map(dto)).thenReturn(mockAccident);
        Mockito.when(accidentRuleRepository.findById(Mockito.anyInt())).thenReturn(mockRule);
        Mockito.when(accidentRepository.save(Mockito.any(Accident.class))).thenReturn(mockAccident);
        var actual = accidentService.addAccident(dto, new String[] {"1"});
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(mockAccident.getName(), actual.getName());
        Assertions.assertEquals(mockAccident.getText(), actual.getText());
        Assertions.assertEquals(mockAccident.getAddress(), actual.getAddress());
        Assertions.assertEquals(1, actual.getRules().size());
        Assertions.assertEquals(mockRule.get().getName(), actual.getRules().get(0).getName());
    }

    @Test
    void whenUpdateAccident() {
        var dto = dataFactory.newAccidentDto();
        var mockRule = Optional.of(Rule.builder()
                                       .id(1)
                                       .name("asd")
                                       .build());
        var mockAccident = Accident.builder()
                                   .id(1)
                                   .name("name")
                                   .text("text")
                                   .address("address")
                                   .accidentType(AccidentType.builder()
                                                             .id(1)
                                                             .name("name")
                                                             .build())
                                   .build();
        Mockito.when(accidentMapper.map(dto)).thenReturn(mockAccident);
        Mockito.when(accidentRuleRepository.findById(Mockito.anyInt())).thenReturn(mockRule);
        Mockito.when(accidentRepository.save(Mockito.any(Accident.class))).thenReturn(mockAccident);
        accidentService.addAccident(dto, new String[] {"1"});
        var updatedDto = dataFactory.updatedAccidentDto();
        var expected = Accident.builder()
                               .id(1)
                               .name("name-after-update")
                               .text("text")
                               .address("address")
                               .accidentType(AccidentType.builder()
                                                         .id(1)
                                                         .name("name-after-update")
                                                         .build())
                               .rules(new ArrayList<>(List.of(Rule.builder()
                                                                  .id(1)
                                                                  .name("name-after-update")
                                                                  .build())))
                               .build();
        Mockito.when(accidentMapper.map(updatedDto)).thenReturn(expected);
        Mockito.when(accidentRepository.findById(Mockito.anyInt()))
               .thenReturn(Optional.of(expected));
        Mockito.when(accidentRepository.save(Mockito.any(Accident.class)))
               .thenReturn(expected);
        Mockito.when(accidentRepository.findByName(Mockito.anyString()))
               .thenReturn(Optional.of(expected));
        accidentService.updateAccident(updatedDto);
        var actual = accidentRepository.findByName(updatedDto.getName());
        actual.ifPresent(actualAccident -> {
            Assertions.assertEquals(expected.getName(), actualAccident.getName());
            Assertions.assertEquals(expected.getText(), actualAccident.getText());
            Assertions.assertEquals(expected.getAddress(), actualAccident.getAddress());
        });
    }

    @Test
    void whenSaveAccidentAndFindHimById() {
        var expected = dataFactory.newAccidentDto();
        var mockRule = Optional.of(Rule.builder()
                                       .id(1)
                                       .name("asd")
                                       .build());
        var mockAccident = Accident.builder()
                                   .id(1)
                                   .name("name")
                                   .text("text")
                                   .address("address")
                                   .accidentType(AccidentType.builder()
                                                             .id(1)
                                                             .name("name")
                                                             .build())
                                   .build();
        Mockito.when(accidentMapper.map(expected)).thenReturn(mockAccident);
        Mockito.when(accidentRuleRepository.findById(Mockito.anyInt()))
               .thenReturn(mockRule);
        Mockito.when(accidentRepository.save(Mockito.any(Accident.class)))
               .thenReturn(mockAccident);
        accidentService.addAccident(expected, new String[] {"1"});

        Mockito.when(accidentRepository.findById(Mockito.anyInt()))
               .thenReturn(Optional.of(mockAccident));
        Mockito.when(accidentMapper.map(Mockito.any(Accident.class)))
               .thenReturn(expected);
        var actual = accidentService.findAccidentById(1);
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getText(), actual.getText());
        Assertions.assertEquals(expected.getAddress(), actual.getAddress());
    }

    @Test
    void whenSaveAccidentAndFindAll() {
        var dto = dataFactory.newAccidentDto();
        var mockRule = Optional.of(Rule.builder()
                                       .id(1)
                                       .name("asd")
                                       .build());
        var mockAccident = Accident.builder()
                                   .id(1)
                                   .name("name")
                                   .text("text")
                                   .address("address")
                                   .accidentType(AccidentType.builder()
                                                             .id(1)
                                                             .name("name")
                                                             .build())
                                   .build();
        Mockito.when(accidentMapper.map(dto)).thenReturn(mockAccident);
        Mockito.when(accidentRuleRepository.findById(Mockito.anyInt())).thenReturn(mockRule);
        Mockito.when(accidentRepository.save(Mockito.any(Accident.class))).thenReturn(mockAccident);
        accidentService.addAccident(dto, new String[] {"1"});

        Mockito.when(accidentRepository.findAll()).thenReturn(List.of(mockAccident));
        var actual = accidentService.findAllAccidents();
        Assertions.assertEquals(1, actual.size());
    }

}