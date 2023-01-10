package ru.practicum.ewm_main.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm_main.events.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}