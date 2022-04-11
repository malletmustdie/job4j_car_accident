package ru.job4j.accident.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplateRepository;

@Service
@RequiredArgsConstructor
public class AccidentJdbcService {

    private final AccidentJdbcTemplateRepository jdbcTemplate;

    public Accident addAccident(Accident accident, String[] ruleIds) {
        return jdbcTemplate.save(accident, ruleIds);
    }

    public void updateAccident(Accident accident) {
        jdbcTemplate.update(accident);
    }

    public Accident findAccidentById(Integer id) {
        return jdbcTemplate.getAccidentById(id);
    }

    public List<Accident> findAllAccidents() {
        return jdbcTemplate.findAllAccidents();
    }

    public AccidentType findAccidentTypeById(Integer id) {
        return jdbcTemplate.getAccidentTypeById(id);
    }

    public List<AccidentType> findAllAccidentTypes() {
        return jdbcTemplate.getAllAccidentTypes();
    }

    public Rule findAccidentRuleById(Integer id) {
        return jdbcTemplate.getAccidentRuleById(id);
    }

    public List<Rule> findAllAccidentRules() {
        return jdbcTemplate.getAllAccidentRules();
    }

}
