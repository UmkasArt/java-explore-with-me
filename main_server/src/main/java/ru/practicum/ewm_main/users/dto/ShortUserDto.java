package ru.practicum.ewm_main.users.dto;

import lombok.*;

@Getter
@Setter
//todo вернуть билдер
@AllArgsConstructor
@NoArgsConstructor
public class ShortUserDto {
    private Long id;

    private String name;
}