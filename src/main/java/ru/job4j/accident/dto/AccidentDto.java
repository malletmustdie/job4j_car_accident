package ru.job4j.accident.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.List;

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

    @NotBlank(message = "Название инцидента не должно быть пустым!")
    private String name;

    @NotBlank(message = "Описание инцидента не должно быть пустым!")
    @Size(min = 10, message = "Описание инцидента должно содержать минимум 10 символов!")
    private String text;

    @NotBlank(message = "Адрес инцидента не должен быть пустым")
    private String address;

    private AccidentType accidentType;

    private List<Rule> rules;

}
