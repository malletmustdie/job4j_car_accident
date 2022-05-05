package ru.job4j.accident.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.job4j.accident.TestDataFactory;
import ru.job4j.accident.annotation.UnitTest;
import ru.job4j.accident.mapper.UserMapper;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.UserRepository;
import ru.job4j.accident.service.UserService;

@UnitTest
class UserServiceImplTest {

    @Autowired
    private TestDataFactory dataFactory;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    void whenSaveNewUser() {
        var userDto = dataFactory.newUserDto();
        var expected = User.builder()
                           .username("root")
                           .password("root")
                           .authority(Authority.builder()
                                               .authority("USER_ROLE").
                                               build())
                           .enabled(true)
                           .build();
        Mockito.when(userMapper.map(userDto)).thenReturn(expected);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(expected);
        var actual = userService.saveUser(userDto);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getUsername(), actual.getUsername());
        Assertions.assertEquals(
                expected.getAuthority().getAuthority(),
                actual.getAuthority().getAuthority()
        );
    }

}