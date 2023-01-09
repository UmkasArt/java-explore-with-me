package ru.practicum.ewm_main.participations.dto;

import ru.practicum.ewm_main.participations.model.Participation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParticipationMapper {
    /*public static ParticipationDto toParticipationDto(Participation participation) {
        return ParticipationDto
                .builder()
                .id(participation.getId())
                .created(participation.getCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .event(participation.getEvent().getId())
                .requester(participation.getRequester().getId())
                .status(participation.getStatus())
                .build();
    }*/

    public static ParticipationDto toParticipationDto(Participation participation) {
        return new ParticipationDto(
                participation.getId(),
                participation.getCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                participation.getEvent().getId(),
                participation.getRequester().getId(),
                participation.getStatus()
        );
    }

    /*public static Participation toParticipation(ParticipationDto participationDto) {
        return Participation
                .builder()
                .id(participationDto.getId())
                .created(LocalDateTime.parse(participationDto.getCreated(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .status(participationDto.getStatus())
                .build();
    }*/

    public static Participation toParticipation(ParticipationDto participationDto) {
        return new Participation(
                participationDto.getId(),
                LocalDateTime.parse(participationDto.getCreated(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                participationDto.getStatus()
        );
    }
}