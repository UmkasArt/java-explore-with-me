package ru.practicum.ewm_main.compilations.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShortCompilationDto {
    @NotBlank
    private String title;

    private Boolean pinned;

    private List<Long> events;
}