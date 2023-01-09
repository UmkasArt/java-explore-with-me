package ru.practicum.ewm_main.compilations.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
//todo возможно вернуть билдер
@AllArgsConstructor
@NoArgsConstructor
public class ShortCompilationDto {
    @NotBlank
    private String title;

    private Boolean pinned;

    private List<Long> events;
}