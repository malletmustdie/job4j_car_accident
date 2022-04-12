package ru.job4j.accident.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.service.AccidentTypeService;

@Service
@RequiredArgsConstructor
public class AccidentTypeServiceImpl implements AccidentTypeService {

    private final AccidentTypeRepository accidentTypeRepository;

    @Override
    @Transactional
    public AccidentType findAccidentTypeById(Integer id) {
        return accidentTypeRepository.findById(id)
                                     .orElse(null);
    }

    @Override
    @Transactional
    public List<AccidentType> findAllAccidentTypes() {
        return accidentTypeRepository.findAll();
    }

}
