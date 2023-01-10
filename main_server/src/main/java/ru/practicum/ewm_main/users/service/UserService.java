package ru.practicum.ewm_main.users.service;

import ru.practicum.ewm_main.users.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers(List<Long> ids, int from, int size);

    UserDto createUser(UserDto userDto);

    void deleteUser(Long id);
}