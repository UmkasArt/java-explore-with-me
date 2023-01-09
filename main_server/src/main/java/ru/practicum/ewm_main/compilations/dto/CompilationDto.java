package ru.practicum.ewm_main.compilations.dto;

import lombok.*;
import ru.practicum.ewm_main.events.dto.ShortEventDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDto {
    private Long id;

    private String title;

    private Boolean pinned;

    private List<ShortEventDto> events;
}