package ru.job4j.accident.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

public interface AccidentRuleRepository extends CrudRepository<Rule, Integer> {

    @Override
    List<Rule> findAll();

}
