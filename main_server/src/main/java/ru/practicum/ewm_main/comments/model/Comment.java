package ru.practicum.ewm_main.comments.model;

import lombok.*;
import ru.practicum.ewm_main.events.model.Event;
import ru.practicum.ewm_main.users.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000, nullable = false)
    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;

    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;
}