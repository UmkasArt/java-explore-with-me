package ru.practicum.ewm_stats.dto;

import ru.practicum.ewm_stats.model.Hit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HitMapper {
    public static Hit toHit(EndpointHit endpointHit) {
        return new Hit(
                endpointHit.getId(),
                endpointHit.getApp(),
                endpointHit.getIp(),
                endpointHit.getUri(),
                LocalDateTime.parse(endpointHit.getTimestamp(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }

    public static ViewStats toViewStats(Hit hit) {
        return new ViewStats(
                hit.getApp(),
                hit.getUri()
        );
    }
}