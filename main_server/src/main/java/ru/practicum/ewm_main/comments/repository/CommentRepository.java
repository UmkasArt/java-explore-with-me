package ru.practicum.ewm_main.comments.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm_main.comments.model.Comment;
import ru.practicum.ewm_main.events.model.Event;
import ru.practicum.ewm_main.users.model.User;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByEvent(Event event, Pageable pageable);

    Page<Comment> findAllByUser(User user, Pageable pageable);

    Optional<Comment> findByIdAndUserId(Long id, Long userId);
}