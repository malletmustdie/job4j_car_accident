package ru.job4j.accident.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.service.AuthorityService;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public Authority findByAuthority(String authority) {
        return authorityRepository.findByAuthority(authority);
    }

}
