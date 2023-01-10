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
}