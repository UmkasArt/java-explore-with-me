package ru.practicum.ewm_main.participations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm_main.participations.model.Participation;
import ru.practicum.ewm_main.participations.model.StatusRequest;

import java.util.List;
import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    Optional<Participation> findByIdAndRequesterId(Long id, Long userId);

    List<Participation> findAllByRequesterId(Long userId);

    Participation findByEventIdAndRequesterId(Long eventId, Long userId);

    List<Participation> findAllByEventIdAndEventInitiatorId(Long eventId, Long userId);

    int countParticipationByEventIdAndStatus(Long eventId, StatusRequest status);
}