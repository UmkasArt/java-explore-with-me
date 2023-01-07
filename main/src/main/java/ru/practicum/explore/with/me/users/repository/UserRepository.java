package ru.practicum.explore.with.me.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explore.with.me.users.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}