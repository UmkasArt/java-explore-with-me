package ru.practicum.ewm_main.events.dto;

import lombok.*;
import ru.practicum.ewm_main.categories.dto.CategoryDto;
import ru.practicum.ewm_main.users.dto.ShortUserDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortEventDto {
    private Long id;

    private String annotation;

    private CategoryDto category;

    private String eventDate;

    private ShortUserDto initiator;

    private Boolean paid;

    private String title;

    private Integer confirmedRequests;

    private Long views;

    public ShortEventDto(Long id, String annotation, CategoryDto category, String eventDate, ShortUserDto initiator, Boolean paid, String title, Long views) {
        this.id = id;
        this.annotation = annotation;
        this.category = category;
        this.eventDate = eventDate;
        this.initiator = initiator;
        this.paid = paid;
        this.title = title;
        this.views = views;
    }
}