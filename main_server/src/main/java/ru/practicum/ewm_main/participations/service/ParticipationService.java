package ru.practicum.ewm_main.participations.service;

import ru.practicum.ewm_main.participations.dto.ParticipationDto;

import java.util.List;

public interface ParticipationService {
    ParticipationDto createParticipationRequest(Long userId, Long eventId);

    List<ParticipationDto> getParticipationRequests(Long userId);

    List<ParticipationDto> getParticipationRequests(Long eventId, Long userId);

    ParticipationDto confirmParticipationRequest(Long eventId, Long userId, Long reqId);

    ParticipationDto rejectParticipationRequest(Long eventId, Long userId, Long reqId);

    ParticipationDto cancelParticipationRequest(Long userId, Long reqId);

}