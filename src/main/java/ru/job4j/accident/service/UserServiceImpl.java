package ru.job4j.accident.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.dto.UserDto;
import ru.job4j.accident.mapper.UserMapper;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public User saveUser(UserDto userDto) {
        var user = userMapper.map(userDto);
        return userRepository.save(user);
    }

}
