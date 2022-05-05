package ru.job4j.accident.service;

import java.util.List;

import ru.job4j.accident.dto.AccidentDto;
import ru.job4j.accident.model.Accident;

public interface AccidentService {

    Accident addAccident(AccidentDto accidentDto, String[] ruleIds);

    void updateAccident(AccidentDto accidentDto);

    AccidentDto findAccidentById(Integer id);

    List<Accident> findAllAccidents();

}
