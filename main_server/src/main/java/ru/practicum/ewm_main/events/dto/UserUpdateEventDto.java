package ru.practicum.ewm_main.events.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateEventDto {
    private Long eventId;

    private String annotation;

    private Long category;

    private String description;

    private String eventDate;

    private Boolean paid;

    private Integer participantLimit;

    private String title;
}