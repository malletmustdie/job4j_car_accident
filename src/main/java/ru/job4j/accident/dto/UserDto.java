package ru.job4j.accident.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.accident.model.Authority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;

    private String password;

    private String username;

    private Authority authority;

    private boolean enabled;

}
