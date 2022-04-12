package ru.job4j.accident.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRuleRepository;
import ru.job4j.accident.service.AccidentRuleService;

@Service
@RequiredArgsConstructor
public class AccidentRuleServiceImpl implements AccidentRuleService {

    private final AccidentRuleRepository accidentRuleRepository;

    @Override
    @Transactional
    public Rule findAccidentRuleById(Integer id) {
        return accidentRuleRepository.findById(id)
                                     .orElse(null);
    }

    @Override
    @Transactional
    public List<Rule> findAllAccidentRules() {
        return accidentRuleRepository.findAll();
    }

}
