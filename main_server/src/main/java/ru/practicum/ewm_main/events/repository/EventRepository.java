package ru.practicum.ewm_main.events.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewm_main.events.model.Event;
import ru.practicum.ewm_main.events.model.State;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByCategoryId(Long categoryId);

    Page<Event> findAllByInitiatorId(Long userId, Pageable pageable);

    @Query("SELECT e FROM Event AS e " +
            "WHERE ((:users) IS NULL OR e.initiator.id IN :users) " +
            "AND ((:states) IS NULL OR e.state IN :states) " +
            "AND ((:categories) IS NULL OR e.category.id IN :categories)")
    Page<Event> searchEventsByAdmin(List<Long> users, List<State> states, List<Long> categories, Pageable pageable);

    @Query("SELECT e FROM Event AS e " +
            "WHERE (lower(e.annotation) like lower(concat('%', :text, '%')) " +
            "OR lower(e.description) like lower(concat('%', :text, '%'))) " +
            "AND ((:categoryIds) IS NULL OR e.category.id IN :categoryIds) " +
            "AND e.paid = :paid " +
            "AND e.state IN :state")
    Page<Event> searchEvents(String text, List<Long> categoryIds, Boolean paid, State state, Pageable pageable);
}