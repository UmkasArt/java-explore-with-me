package ru.practicum.ewm_main.participations.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_main.participations.dto.ParticipationDto;
import ru.practicum.ewm_main.participations.service.ParticipationService;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/requests")
public class ParticipationController {
    private final ParticipationService participationService;

    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @GetMapping
    public List<ParticipationDto> getParticipationRequests(@PathVariable Long userId) {
        return participationService.getParticipationRequests(userId);
    }

    @PostMapping
    public ParticipationDto createParticipationRequest(@PathVariable Long userId,
                                                       @RequestParam Long eventId) {
        return participationService.createParticipationRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationDto cancelParticipationRequest(@PathVariable Long userId,
                                                       @PathVariable Long requestId) {
        return participationService.cancelParticipationRequest(userId, requestId);
    }
}