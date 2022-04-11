package ru.job4j.accident.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccidentDto {

    private Integer id;

    private String name;

    private String text;

    private String address;

    private AccidentType accidentType;

    private Set<Rule> rules;

}
