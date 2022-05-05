package ru.job4j.accident.service.impl;

import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.dto.AccidentDto;
import ru.job4j.accident.mapper.AccidentMapper;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentRuleRepository;
import ru.job4j.accident.service.AccidentService;

@Service
@RequiredArgsConstructor
public class AccidentServiceImpl implements AccidentService {

    private final AccidentRepository accidentRepository;

    private final AccidentRuleRepository accidentRuleRepository;

    private final AccidentMapper accidentMapper;

    @Override
    @Transactional
    public Accident addAccident(AccidentDto accidentDto, String[] ruleIds) {
        Accident accident = accidentMapper.map(accidentDto);
        Accident savedAccident = accidentRepository.save(accident);
        Arrays.stream(ruleIds)
              .map(id -> accidentRuleRepository.findById(Integer.parseInt(id)).orElse(null))
              .forEach(savedAccident::addRule);
        return savedAccident;
    }

    @Override
    @Transactional
    public void updateAccident(AccidentDto accidentDto) {
        var accidentEntity = accidentMapper.map(accidentDto);
        accidentRepository.findById(accidentEntity.getId())
                          .ifPresent(accidentFromDb -> {
                              accidentFromDb.setName(accidentEntity.getName());
                              accidentFromDb.setText(accidentEntity.getText());
                              accidentFromDb.setAddress(accidentEntity.getAddress());
                              accidentFromDb.setAccidentType(accidentEntity.getAccidentType());
                              accidentFromDb.getRules().addAll(accidentEntity.getRules());
                              accidentRepository.save(accidentFromDb);
                          });
    }

    @Override
    @Transactional
    public AccidentDto findAccidentById(Integer id) {
        return accidentRepository.findById(id)
                                 .map(accidentMapper::map)
                                 .orElse(null);
    }

    @Override
    @Transactional
    public List<Accident> findAllAccidents() {
        return accidentRepository.findAll();
    }

}
