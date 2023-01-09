package ru.practicum.ewm_stats.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//todo возможно вернуть билдер
public class EndpointHit {
    private Long id;

    private String app;

    private String uri;

    private String ip;

    private String timestamp;
}