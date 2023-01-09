package ru.practicum.ewm_main.events.dto;

import ru.practicum.ewm_main.events.model.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ru.practicum.ewm_main.categories.dto.CategoryMapper.toCategory;
import static ru.practicum.ewm_main.categories.dto.CategoryMapper.toCategoryDto;
import static ru.practicum.ewm_main.events.dto.LocationMapper.toLocationDto;
import static ru.practicum.ewm_main.events.model.State.PENDING;
import static ru.practicum.ewm_main.users.dto.UserMapper.toShortUserDto;

public class EventMapper {
    public static EventDto toEventDto(Event event) {
        return new EventDto(
                event.getId(),
                event.getAnnotation(),
                toCategoryDto(event.getCategory()),
                event.getCreatedOn().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                event.getDescription(),
                event.getEventDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                toShortUserDto(event.getInitiator()),
                toLocationDto(event.getLocation()),
                event.getPaid(),
                event.getParticipantLimit(),
                event.getPublishedOn(),
                event.getRequestModeration(),
                event.getState(),
                event.getTitle(),
                event.getViews()
        );
    }

    public static Event toEvent(EventDto eventDto) {
        return new Event(
                eventDto.getId(),
                eventDto.getAnnotation(),
                toCategory(eventDto.getCategory()),
                LocalDateTime.parse(eventDto.getCreatedOn(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                eventDto.getDescription(),
                LocalDateTime.parse(eventDto.getEventDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                eventDto.getPaid(),
                eventDto.getParticipantLimit(),
                eventDto.getPublishedOn(),
                eventDto.getRequestModeration(),
                eventDto.getState(),
                eventDto.getTitle()
        );
    }

    public static ShortEventDto toShortEventDto(Event event) {
        return new ShortEventDto(
                event.getId(),
                event.getAnnotation(),
                toCategoryDto(event.getCategory()),
                event.getEventDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                toShortUserDto(event.getInitiator()),
                event.getPaid(),
                event.getTitle(),
                event.getViews()
        );
    }

    public static Event toEvent(ShortEventDto shortEventDto) {
        return new Event(
                shortEventDto.getId(),
                shortEventDto.getAnnotation(),
                toCategory(shortEventDto.getCategory()),
                LocalDateTime.parse(shortEventDto.getEventDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                shortEventDto.getPaid(),
                shortEventDto.getTitle()
        );
    }


    public static Event toEvent(NewEventDto eventDto) {
        return new Event(
                eventDto.getAnnotation(),
                eventDto.getDescription(),
                LocalDateTime.parse(eventDto.getEventDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                eventDto.getPaid(),
                eventDto.getParticipantLimit(),
                eventDto.getRequestModeration(),
                eventDto.getTitle(),
                PENDING,
                LocalDateTime.now()
        );
    }
}