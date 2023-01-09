package ru.practicum.ewm_main.events.dto;

import lombok.*;

@Getter
@Setter
//todo вернуть билдер
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private float lat;

    private float lon;
}