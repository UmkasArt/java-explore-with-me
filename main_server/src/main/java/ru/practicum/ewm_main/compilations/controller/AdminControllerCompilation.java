package ru.practicum.ewm_main.compilations.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_main.compilations.dto.CompilationDto;
import ru.practicum.ewm_main.compilations.dto.ShortCompilationDto;
import ru.practicum.ewm_main.compilations.service.CompilationsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/compilations")
public class AdminControllerCompilation {
    private final CompilationsService compilationService;

    public AdminControllerCompilation(CompilationsService compilationService) {
        this.compilationService = compilationService;
    }

    @PostMapping
    public CompilationDto createCompilation(@Valid @RequestBody ShortCompilationDto compilationDto) {
        return compilationService.createCompilation(compilationDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCompilation(@PathVariable Long id) {
        compilationService.deleteCompilation(id);
    }

    @DeleteMapping("/{id}/events/{eventId}")
    public void deleteEventFromCompilation(@PathVariable Long id,
                                           @PathVariable Long eventId) {
        compilationService.deleteEventFromCompilation(id, eventId);
    }

    @PatchMapping("/{id}/events/{eventId}")
    public void addEventToCompilation(@PathVariable Long id,
                                      @PathVariable Long eventId) {
        compilationService.addEventToCompilation(id, eventId);
    }

    @DeleteMapping("/{id}/pin")
    public void deleteCompilationFromMainPage(@PathVariable Long id) {
        compilationService.deleteCompilationFromMainPage(id);
    }

    @PatchMapping("/{id}/pin")
    public void addCompilationToMainPage(@PathVariable Long id) {
        compilationService.addCompilationToMainPage(id);
    }
}