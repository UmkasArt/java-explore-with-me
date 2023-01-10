package ru.practicum.ewm_main.events.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_main.clients.EventClient;
import ru.practicum.ewm_main.events.dto.EventDto;
import ru.practicum.ewm_main.events.dto.ShortEventDto;
import ru.practicum.ewm_main.events.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/events")
public class PublicEventController {
    private final EventService eventService;
    private final EventClient eventClient;

    public PublicEventController(EventService eventService, EventClient eventClient) {
        this.eventService = eventService;
        this.eventClient = eventClient;
    }

    @GetMapping
    public List<ShortEventDto> getEvents(@RequestParam(required = false) String text,
                                         @RequestParam(required = false) List<Long> categoryIds,
                                         @RequestParam(required = false) Boolean paid,
                                         @RequestParam(required = false) String rangeStart,
                                         @RequestParam(required = false) String rangeEnd,
                                         @RequestParam (defaultValue = "false") Boolean onlyAvailable,
                                         @RequestParam(required = false) String sort,
                                         @RequestParam (defaultValue = "0") int from,
                                         @RequestParam (defaultValue = "10") int size,
                                         HttpServletRequest httpServletRequest) {
        eventClient.createHit(httpServletRequest);
        return eventService.getEvents(text, categoryIds, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    @GetMapping("/{id}")
    public EventDto getEvent(@PathVariable Long id,
                             HttpServletRequest httpServletRequest) {
        eventClient.createHit(httpServletRequest);
        return eventService.getEvent(id);
    }
}