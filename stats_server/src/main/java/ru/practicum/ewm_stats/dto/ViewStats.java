package ru.practicum.ewm_stats.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewStats {
    private String app;
    private String uri;

    private Long hits;

    public ViewStats(String app, String uri) {
        this.app = app;
        this.uri = uri;
        this.hits = 0L;
    }
}