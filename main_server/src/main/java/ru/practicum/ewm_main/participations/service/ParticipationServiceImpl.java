package ru.practicum.ewm_main.participations.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm_main.events.model.Event;
import ru.practicum.ewm_main.events.repository.EventRepository;
import ru.practicum.ewm_main.exception.BadRequestException;
import ru.practicum.ewm_main.exception.NotFoundException;
import ru.practicum.ewm_main.participations.dto.ParticipationDto;
import ru.practicum.ewm_main.participations.dto.ParticipationMapper;
import ru.practicum.ewm_main.participations.model.Participation;
import ru.practicum.ewm_main.participations.repository.ParticipationRepository;
import ru.practicum.ewm_main.users.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm_main.events.model.State.PUBLISHED;
import static ru.practicum.ewm_main.participations.model.StatusRequest.*;

@Service
@Transactional(readOnly = true)
public class ParticipationServiceImpl implements ParticipationService {
    private final ParticipationRepository participationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public ParticipationServiceImpl(ParticipationRepository participationRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.participationRepository = participationRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    @Override
    public ParticipationDto createParticipationRequest(Long userId, Long eventId) {
        if (participationRepository.findByEventIdAndRequesterId(eventId, userId) != null) {
            throw new BadRequestException("Participation request already exist");
        }
        /*Participation participation = Participation
                .builder()
                .created(LocalDateTime.now())
                .requester(userRepository.findById(userId)
                        .orElseThrow(() -> new NotFoundException("user with id = " + userId + " not found")))
                .event(eventRepository.findById(eventId)
                        .orElseThrow(() -> new NotFoundException("event with id = " + eventId + " not found")))
                .status(CONFIRMED)
                .build();*/
        Participation participation = new Participation(
                LocalDateTime.now(),
                userRepository.findById(userId)
                        .orElseThrow(() -> new NotFoundException("User with id = " + userId + " not found")),
                eventRepository.findById(eventId)
                        .orElseThrow(() -> new NotFoundException("Event with id = " + eventId + " not found")),
                CONFIRMED
        );

        if (userId.equals(participation.getEvent().getInitiator().getId())){
            throw new BadRequestException("Requester can not be initiator of event");
        }
        if (!participation.getEvent().getState().equals(PUBLISHED)) {
            throw new BadRequestException("Event not published");
        }
        if (participation.getEvent().getParticipantLimit() <= participationRepository
                .countParticipationByEventIdAndStatus(eventId, CONFIRMED)) {
            throw new BadRequestException("The limit of requests for participation has been exhausted");
        }
        if (Boolean.TRUE.equals(participation.getEvent().getRequestModeration())) {
            participation.setStatus(PENDING);
        }
        return ParticipationMapper.toParticipationDto(participationRepository.save(participation));
    }


    @Override
    public List<ParticipationDto> getParticipationRequests(Long userId) {
        return participationRepository.findAllByRequesterId(userId)
                .stream()
                .map(ParticipationMapper::toParticipationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParticipationDto> getParticipationRequests(Long eventId, Long userId) {
        return participationRepository.findAllByEventIdAndEventInitiatorId(eventId, userId)
                .stream()
                .map(ParticipationMapper::toParticipationDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ParticipationDto confirmParticipationRequest(Long eventId, Long userId, Long reqId) {
        Participation participation = checkAndGetParticipation(reqId);
        Event event = checkAndGetEvent(eventId);
        equalsOfParameters(userId, event, participation);
        if (!participation.getStatus().equals(PENDING)) {
            throw new BadRequestException("Only participation request with status pending can be approval");
        }
        if (event.getParticipantLimit() <= participationRepository.countParticipationByEventIdAndStatus(eventId, CONFIRMED)) {
            participation.setStatus(REJECTED);
        } else {
            participation.setStatus(CONFIRMED);
        }
        return ParticipationMapper.toParticipationDto(participationRepository.save(participation));
    }

    @Transactional
    @Override
    public ParticipationDto rejectParticipationRequest(Long eventId, Long userId, Long reqId) {
        Participation participation = checkAndGetParticipation(reqId);
        Event event = checkAndGetEvent(eventId);
        equalsOfParameters(userId, event, participation);
        participation.setStatus(REJECTED);
        return ParticipationMapper.toParticipationDto(participationRepository.save(participation));
    }

    @Transactional
    @Override
    public ParticipationDto cancelParticipationRequest(Long userId, Long reqId) {
        Participation participation = participationRepository.findByIdAndRequesterId(reqId, userId)
                .orElseThrow(() -> new BadRequestException("Only owner can cancel participation request"));
        participation.setStatus(CANCELED);
        return ParticipationMapper.toParticipationDto(participationRepository.save(participation));
    }


    private Participation checkAndGetParticipation(Long id) {
        return participationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Participation request with id = " + id + " not found"));
    }

    private Event checkAndGetEvent(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Event with id = " + id + " not found"));
    }

    private void equalsOfParameters(Long userId, Event event, Participation participation) {
        if (!event.getInitiator().getId().equals(userId)) {
            throw new NotFoundException("Only initiator of event can confirm or reject participation request to this event");
        }
        if (!event.getId().equals(participation.getEvent().getId())) {
            throw new NotFoundException("EventId not equals eventId of participation request");
        }
    }
}