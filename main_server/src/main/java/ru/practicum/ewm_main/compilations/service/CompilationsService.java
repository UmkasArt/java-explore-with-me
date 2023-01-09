package ru.practicum.ewm_main.compilations.service;

import ru.practicum.ewm_main.compilations.dto.CompilationDto;
import ru.practicum.ewm_main.compilations.dto.ShortCompilationDto;

import java.util.List;

public interface CompilationsService {
    CompilationDto createCompilation(ShortCompilationDto compilationDto);

    List<CompilationDto> getCompilations(Boolean pinned, int from, int size);

    CompilationDto getCompilation(Long id);

    void addEventToCompilation(Long id, Long eventId);

    void addCompilationToMainPage(Long id);

    void deleteCompilation(Long id);

    void deleteEventFromCompilation(Long id, Long eventId);

    void deleteCompilationFromMainPage(Long id);
}