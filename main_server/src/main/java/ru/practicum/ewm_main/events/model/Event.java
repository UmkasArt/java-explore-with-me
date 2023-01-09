package ru.practicum.ewm_main.events.model;

import lombok.*;
import ru.practicum.ewm_main.categories.model.Category;
import ru.practicum.ewm_main.users.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String annotation;

    @ManyToOne
    private Category category;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(length = 1000)
    private String description;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @ManyToOne
    private User initiator;

    @ManyToOne
    private Location location;

    @Column(nullable = false)
    private Boolean paid;

    @Column(name = "participant_limit")
    private Integer participantLimit;

    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    @Column(name = "request_moderation")
    private Boolean requestModeration;

    @Enumerated(EnumType.STRING)
    private State state;

    @Column(length = 255, nullable = false)
    private String title;

    private Long views = 0L;

    public Event(Long id, String annotation, Category category, LocalDateTime createdOn, String description, LocalDateTime eventDate, Boolean paid, Integer participantLimit, LocalDateTime publishedOn, Boolean requestModeration, State state, String title) {
        this.id = id;
        this.annotation = annotation;
        this.category = category;
        this.createdOn = createdOn;
        this.description = description;
        this.eventDate = eventDate;
        this.paid = paid;
        this.participantLimit = participantLimit;
        this.publishedOn = publishedOn;
        this.requestModeration = requestModeration;
        this.state = state;
        this.title = title;
    }

    public Event(Long id, String annotation, Category category, LocalDateTime eventDate, Boolean paid, String title) {
        this.id = id;
        this.annotation = annotation;
        this.category = category;
        this.eventDate = eventDate;
        this.paid = paid;
        this.title = title;
    }

    public Event(String annotation, String description, LocalDateTime eventDate, Boolean paid, Integer participantLimit, Boolean requestModeration, String title, State state, LocalDateTime createdOn) {
        this.annotation = annotation;
        this.description = description;
        this.eventDate = eventDate;
        this.paid = paid;
        this.participantLimit = participantLimit;
        this.requestModeration = requestModeration;
        this.title = title;
        this.state = state;
        this.createdOn = createdOn;
    }
}