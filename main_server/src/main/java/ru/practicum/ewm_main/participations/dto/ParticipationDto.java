package ru.practicum.ewm_main.participations.dto;

import lombok.*;
import ru.practicum.ewm_main.participations.model.StatusRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationDto {
    private Long id;

    private String created;

    private Long event;

    private Long requester;

    private StatusRequest status;
}