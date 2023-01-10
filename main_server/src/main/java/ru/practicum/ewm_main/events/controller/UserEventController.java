package ru.practicum.ewm_main.events.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_main.events.dto.EventDto;
import ru.practicum.ewm_main.events.dto.NewEventDto;
import ru.practicum.ewm_main.events.dto.ShortEventDto;
import ru.practicum.ewm_main.events.dto.UserUpdateEventDto;
import ru.practicum.ewm_main.events.service.EventService;
import ru.practicum.ewm_main.participations.dto.ParticipationDto;
import ru.practicum.ewm_main.participations.service.ParticipationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/events")
public class UserEventController {
    private final EventService eventService;
    private final ParticipationService participationService;

    public UserEventController(EventService eventService, ParticipationService participationService) {
        this.eventService = eventService;
        this.participationService = participationService;
    }

    @GetMapping
    public List<ShortEventDto> getUserEvents(@PathVariable Long userId,
                                             @RequestParam (defaultValue = "0") int from,
                                             @RequestParam (defaultValue = "10") int size) {
        return eventService.getUserEvents(userId, from, size);
    }

    @PatchMapping
    public EventDto updateEvent(@PathVariable Long userId,
                                @RequestBody UserUpdateEventDto eventDto) {
        return eventService.updateEvent(userId, eventDto);
    }

    @PostMapping
    public EventDto createEvent(@PathVariable Long userId,
                                @Valid @RequestBody NewEventDto eventDto) {
        return eventService.createEvent(userId, eventDto);
    }

    @GetMapping("/{eventId}")
    public EventDto getEventByUser(@PathVariable Long userId,
                                   @PathVariable Long eventId) {
        return eventService.getEventByUser(eventId, userId);
    }

    @PatchMapping("/{eventId}")
    public EventDto cancelEventByUser(@PathVariable Long userId,
                                      @PathVariable Long eventId) {
        return eventService.cancelEventByUser(eventId, userId);
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationDto> getParticipationRequests(@PathVariable Long userId,
                                                           @PathVariable Long eventId) {
        return participationService.getParticipationRequests(eventId, userId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public ParticipationDto confirmParticipationRequest(@PathVariable Long userId,
                                                        @PathVariable Long eventId,
                                                        @PathVariable Long reqId) {
        return participationService.confirmParticipationRequest(eventId, userId, reqId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/reject")
    public ParticipationDto rejectParticipationRequest(@PathVariable Long userId,
                                                       @PathVariable Long eventId,
                                                       @PathVariable Long reqId) {
        return participationService.rejectParticipationRequest(eventId, userId, reqId);
    }
}