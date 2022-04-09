package ru.job4j.accident.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accident {

    private Integer id;
    private String name;
    private String text;
    private String address;
    private AccidentType accidentType;
    private Set<Rule> rules;

    public void addRule(Rule rule) {
        rules.add(rule);
    }

}
