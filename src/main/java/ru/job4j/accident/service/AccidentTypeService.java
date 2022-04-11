package ru.job4j.accident.service;

import java.util.List;

import ru.job4j.accident.model.AccidentType;

public interface AccidentTypeService {

    AccidentType findAccidentTypeById(Integer id);

    List<AccidentType> findAllAccidentTypes();

}
