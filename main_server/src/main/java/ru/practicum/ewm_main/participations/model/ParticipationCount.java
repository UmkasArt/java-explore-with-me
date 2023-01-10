package ru.practicum.ewm_main.participations.model;

import lombok.*;




@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationCount {
    private Long eventId;
    private Long count;
}
