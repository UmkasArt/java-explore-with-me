package ru.practicum.ewm_main.participations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewm_main.participations.model.Participation;
import ru.practicum.ewm_main.participations.model.ParticipationCount;
import ru.practicum.ewm_main.participations.model.StatusRequest;

import java.util.List;
import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    Optional<Participation> findByIdAndRequesterId(Long id, Long userId);

    List<Participation> findAllByRequesterId(Long userId);

    Participation findByEventIdAndRequesterId(Long eventId, Long userId);

    List<Participation> findAllByEventIdAndEventInitiatorId(Long eventId, Long userId);

    int countParticipationByEventIdAndStatus(Long eventId, StatusRequest status);

    @Query("SELECT new ru.practicum.ewm_main.participations.model.ParticipationCount(p.event.id, COUNT(p.event.id)) "
            + "FROM Participation AS p WHERE p.status = ?1 GROUP BY p.event.id")
    List<ParticipationCount> findCountParticipationByEventId(StatusRequest status);
}