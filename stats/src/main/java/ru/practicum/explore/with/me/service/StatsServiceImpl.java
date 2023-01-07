package ru.practicum.explore.with.me.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explore.with.me.dto.StatsHitDto;
import ru.practicum.explore.with.me.dto.StatsMapper;
import ru.practicum.explore.with.me.dto.StatsViewDto;
import ru.practicum.explore.with.me.model.StatsHit;
import ru.practicum.explore.with.me.repository.StatsRepository;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class StatsServiceImpl implements StatsService {

    private StatsRepository statsRepository;

    @Override
    @Transactional
    public void addHit(StatsHitDto statsHitDto) {
        try {
            StatsHit statsHit = StatsMapper.toStatsHit(statsHitDto);
            statsRepository.save(statsHit);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StatsViewDto> getViewStats(String start, String end, List<String> uris, Boolean unique) {
        LocalDateTime rangeStart = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime rangeEnd = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (unique) {
            return statsRepository.countTotalIpDistinct(rangeStart, rangeEnd, uris);
        } else {
            return statsRepository.countTotalIp(rangeStart, rangeEnd, uris);
        }
    }

}