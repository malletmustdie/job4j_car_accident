package ru.job4j.accident.service;

import ru.job4j.accident.dto.UserDto;
import ru.job4j.accident.model.User;

public interface UserService {

    User saveUser(UserDto user);
}

