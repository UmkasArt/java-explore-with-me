package ru.practicum.ewm_stats.dto;

import ru.practicum.ewm_stats.model.Hit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HitMapper {
    /*public static Hit toHit(EndpointHit endpointHit) {
        return Hit
                .builder()
                .id(endpointHit.getId())
                .app(endpointHit.getApp())
                .ip(endpointHit.getIp())
                .uri(endpointHit.getUri())
                .timestamp(LocalDateTime.parse(endpointHit.getTimestamp(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }*/
    //todo возможно вернуть

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

    /*public static ViewStats toViewStats(Hit hit) {
        return ViewStats
                .builder()
                .app(hit.getApp())
                .uri(hit.getUri())
                .build();
    }*/

    public static ViewStats toViewStats(Hit hit) {
        return new ViewStats(
                hit.getApp(),
                hit.getApp(),
                Long.valueOf(hit.getUri())
        );
    }
}