package ru.practicum.ewm_main.compilations.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm_main.compilations.dto.CompilationDto;
import ru.practicum.ewm_main.compilations.dto.CompilationsMapper;
import ru.practicum.ewm_main.compilations.dto.ShortCompilationDto;
import ru.practicum.ewm_main.compilations.model.Compilation;
import ru.practicum.ewm_main.compilations.repository.CompilationRepository;
import ru.practicum.ewm_main.events.dto.ShortEventDto;
import ru.practicum.ewm_main.events.model.Event;
import ru.practicum.ewm_main.events.repository.EventRepository;
import ru.practicum.ewm_main.exception.BadRequestException;
import ru.practicum.ewm_main.exception.NotFoundException;
import ru.practicum.ewm_main.participations.repository.ParticipationRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm_main.participations.model.StatusRequest.CONFIRMED;

@Transactional(readOnly = true)
@Service
public class CompilationsServiceImpl implements CompilationsService {
    private final CompilationRepository compilationRepository;
    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;

    public CompilationsServiceImpl(CompilationRepository compilationRepository, EventRepository eventRepository, ParticipationRepository participationRepository) {
        this.compilationRepository = compilationRepository;
        this.eventRepository = eventRepository;
        this.participationRepository = participationRepository;
    }

    @Override
    public List<CompilationDto> getCompilations(Boolean pinned, int from, int size) {
        if (pinned == null) {
            return compilationRepository.findAll(PageRequest.of(from / size, size))
                    .stream()
                    .map(CompilationsMapper::toCompilationDto)
                    .collect(Collectors.toList());
        }
        return compilationRepository.findAllByPinned(pinned, PageRequest.of(from / size, size))
                .stream()
                .map(CompilationsMapper::toCompilationDto)
                .map(this::setViewsAndConfirmedRequests)
                .collect(Collectors.toList());
    }

    @Override
    public CompilationDto getCompilation(Long id) {
        return setViewsAndConfirmedRequests(CompilationsMapper.toCompilationDto(getAndCheckCompilation(id)));
    }

    @Transactional
    @Override
    public CompilationDto createCompilation(ShortCompilationDto compilationDto) {
        Compilation compilation = CompilationsMapper.toCompilation(compilationDto);
        List<Event> events = eventRepository.findAllById(compilationDto.getEvents());
        compilation.setEvents(events);
        return setViewsAndConfirmedRequests(CompilationsMapper.toCompilationDto(compilationRepository.save(compilation)));
    }

    @Transactional
    @Override
    public void deleteCompilation(Long id) {
        compilationRepository.delete(getAndCheckCompilation(id));
    }

    @Transactional
    @Override
    public void deleteEventFromCompilation(Long id, Long eventId) {
        Compilation compilation = getAndCheckCompilation(id);
        compilation.getEvents().remove(getAndCheckEvent(eventId));
        compilationRepository.save(compilation);
    }

    @Transactional
    @Override
    public void addEventToCompilation(Long id, Long eventId) {
        Compilation compilation = getAndCheckCompilation(id);
        compilation.getEvents().add(getAndCheckEvent(eventId));
        compilationRepository.save(compilation);
    }

    @Transactional
    @Override
    public void deleteCompilationFromMainPage(Long id) {
        Compilation compilation = getAndCheckCompilation(id);
        compilation.setPinned(false);
        compilationRepository.save(compilation);
    }

    @Transactional
    @Override
    public void addCompilationToMainPage(Long id) {
        Compilation compilation = getAndCheckCompilation(id);
        compilation.setPinned(true);
        compilationRepository.save(compilation);
    }

    private ShortEventDto setConfirmedRequests(ShortEventDto eventDto) {
        eventDto.setConfirmedRequests(participationRepository.countParticipationByEventIdAndStatus(eventDto.getId(),
                CONFIRMED));
        return eventDto;
    }

    private CompilationDto setViewsAndConfirmedRequests(CompilationDto compilationDto) {
        compilationDto.setEvents(compilationDto.getEvents()
                .stream()
                .map(this::setConfirmedRequests)
                .collect(Collectors.toList()));
        return compilationDto;
    }

    private Event getAndCheckEvent(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Event with id = " + id + " not found"));
    }

    private Compilation getAndCheckCompilation(Long id) {
        return compilationRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Compilation with id = " + id + " not found"));
    }
}