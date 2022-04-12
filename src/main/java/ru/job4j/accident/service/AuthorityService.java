package ru.job4j.accident.service;

import ru.job4j.accident.model.Authority;

public interface AuthorityService {

    Authority findByAuthority(String authority);
}
