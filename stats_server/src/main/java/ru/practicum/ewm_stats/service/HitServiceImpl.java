package ru.practicum.ewm_stats.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.practicum.ewm_stats.dto.EndpointHit;
import ru.practicum.ewm_stats.dto.ViewStats;
import ru.practicum.ewm_stats.model.Hit;
import ru.practicum.ewm_stats.repository.HitRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static ru.practicum.ewm_stats.dto.HitMapper.toHit;
import static ru.practicum.ewm_stats.dto.HitMapper.toViewStats;


@Service
@Transactional(readOnly = true)
public class HitServiceImpl implements HitService {
    private final HitRepository hitRepository;

    public HitServiceImpl(HitRepository hitRepository) {
        this.hitRepository = hitRepository;
    }

    @Transactional
    @Override
    public void saveHit(EndpointHit endpointHit) {
        hitRepository.save(toHit(endpointHit));
    }

    @Override
    public List<ViewStats> getStats(String start, String end, List<String> uris, Boolean uniq) {
        LocalDateTime startDate = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endDate = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<ViewStats> hits;
        List<ViewStats> list = new ArrayList<>();
        if (uniq) {
            for (ViewStats viewStats : hitRepository.findDistinct(startDate, endDate)) {
                viewStats.setHits(Long.valueOf(countViewsByUri(viewStats.getUri())));
                list.add(viewStats);
            }
        } else {
            for (Hit hit : hitRepository.findAllByTimestampBetween(startDate, endDate)) {
                ViewStats viewStats = toViewStats(hit);
                viewStats.setHits(Long.valueOf(countViewsByUri(viewStats.getUri())));
                list.add(viewStats);
            }
        }
        hits = list;
        if (uris == null) {
            return hits;
        } else {
            for (ViewStats viewStats : hits) {
                ViewStats stats = filterByUris(viewStats, uris);
                if (stats != null) {
                    list.add(stats);
                }
            }
            return list;
        }
    }

    private Integer countViewsByUri(String uri) {
        return hitRepository.countByUri(uri);
    }

    private ViewStats filterByUris(ViewStats view, List<String> uris) {
        if (uris.contains(view.getUri())) {
            return view;
        } else {
            return null;
        }
    }
}