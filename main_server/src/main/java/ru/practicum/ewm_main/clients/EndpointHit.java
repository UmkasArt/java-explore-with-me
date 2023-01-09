package ru.practicum.ewm_main.clients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EndpointHit {
    private String app;

    private String uri;

    private String ip;

    private String timestamp;
}