package ru.practicum.ewm_main.users.dto;

import ru.practicum.ewm_main.users.model.User;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
    public static User toUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail()
        );
    }

    public static ShortUserDto toShortUserDto(User user) {
        return new ShortUserDto(
                user.getId(),
                user.getName()
        );
    }
}