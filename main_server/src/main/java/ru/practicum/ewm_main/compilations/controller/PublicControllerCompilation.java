package ru.practicum.ewm_main.compilations.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_main.compilations.dto.CompilationDto;
import ru.practicum.ewm_main.compilations.service.CompilationsService;

import java.util.List;

@RestController
@RequestMapping("/compilations")
public class PublicControllerCompilation {
    private final CompilationsService compilationService;

    public PublicControllerCompilation(CompilationsService compilationService) {
        this.compilationService = compilationService;
    }

    @GetMapping
    public List<CompilationDto> getCompilations(@RequestParam (required = false) Boolean pinned,
                                                @RequestParam (defaultValue = "0") int from,
                                                @RequestParam (defaultValue = "10") int size) {
        return compilationService.getCompilations(pinned, from, size);
    }

    @GetMapping("/{id}")
    public CompilationDto getCompilation(@PathVariable Long id) {
        return compilationService.getCompilation(id);
    }
}