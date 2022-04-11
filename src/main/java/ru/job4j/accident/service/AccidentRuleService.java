package ru.job4j.accident.service;

import java.util.List;

import ru.job4j.accident.model.Rule;

public interface AccidentRuleService {

    Rule findAccidentRuleById(Integer id);

    List<Rule> findAllAccidentRules();

}
