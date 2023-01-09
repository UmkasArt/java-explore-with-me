package ru.practicum.ewm_main.compilations.dto;

import ru.practicum.ewm_main.compilations.model.Compilation;
import ru.practicum.ewm_main.events.dto.EventMapper;


import java.util.stream.Collectors;

public class CompilationsMapper {

    public static Compilation toCompilation(CompilationDto compilationDto) {
        return new Compilation(
                compilationDto.getId(),
                compilationDto.getTitle(),
                compilationDto.getPinned(),
                compilationDto.getEvents().stream().map(EventMapper::toEvent).collect(Collectors.toList())
        );
    }
    public static CompilationDto toCompilationDto(Compilation compilation) {
        return new CompilationDto(
                compilation.getId(),
                compilation.getTitle(),
                compilation.getPinned(),
                compilation.getEvents().stream().map(EventMapper::toShortEventDto).collect(Collectors.toList())
        );
    }
    public static Compilation toCompilation(ShortCompilationDto compilationDto) {
        return new Compilation(
                compilationDto.getTitle(),
                compilationDto.getPinned()
        );
    }
}