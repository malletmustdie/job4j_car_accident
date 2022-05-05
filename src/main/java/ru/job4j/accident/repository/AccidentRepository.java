package ru.job4j.accident.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Override
    List<Accident> findAll();

    Optional<Accident> findByName(String name);

}
