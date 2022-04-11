package ru.job4j.accident.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMemRepository;

@Service
@RequiredArgsConstructor
public class AccidentMemService {

    private final AccidentMemRepository accidentMem;

    public void addAccident(Accident accident, String[] ruleIds) {
        setRulesOnAccident(accident, ruleIds);
        accidentMem.add(accident);
    }

    public void updateAccident(Accident accident) {
        accidentMem.update(accident.getId(), accident);
    }

    public Accident findAccidentById(Integer id) {
        return accidentMem.getById(id);
    }

    public List<Accident> getAllAccidents() {
        return accidentMem.findAllAccidents();
    }

    public List<AccidentType> findAllAccidentTypes() {
        return accidentMem.getAllAccidentTypes();
    }

    public AccidentType findAccidentTypeById(Integer id) {
        return accidentMem.getAccidentTypeById(id);
    }

    public Rule findAccidentRuleById(Integer id) {
        return accidentMem.getAccidentRule(id);
    }

    public List<Rule> findAllAccidentRules() {
        return accidentMem.getAllAccidentRules();
    }

    private void setRulesOnAccident(Accident accident, String[] ruleIds) {
        accident.setRules(new HashSet<>());
        Arrays.stream(ruleIds)
              .map(id -> accidentMem.getAccidentRule(Integer.parseInt(id)))
              .forEachOrdered(accident::addRule);
    }

}
