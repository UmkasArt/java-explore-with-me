package ru.practicum.ewm_stats.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_stats.dto.EndpointHit;
import ru.practicum.ewm_stats.dto.ViewStats;
import ru.practicum.ewm_stats.service.HitService;

import java.util.List;

@RestController
public class HitController {
    private final HitService hitService;

    public HitController(HitService hitService) {
        this.hitService = hitService;
    }

    @GetMapping("stats")
    public List<ViewStats> getStats(@RequestParam String start,
                                    @RequestParam String end,
                                    @RequestParam(required = false) List<String> uris,
                                    @RequestParam(defaultValue = "false") Boolean uniq) {
        return hitService.getStats(start, end, uris, uniq);
    }

    @PostMapping("/hit")
    public void saveHit(@RequestBody EndpointHit endpointHit) {
        hitService.saveHit(endpointHit);
    }
}