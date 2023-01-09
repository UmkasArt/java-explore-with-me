package ru.practicum.ewm_main.users.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
//todo вернуть билдер
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;
}