package ru.practicum.ewm_main.participations.model;

import lombok.*;
import ru.practicum.ewm_main.events.model.Event;
import ru.practicum.ewm_main.users.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "participations")
@Getter
@Setter
//todo возможно надо будет вернуть билдер
@AllArgsConstructor
@NoArgsConstructor
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "requester")
    private User requester;

    @Enumerated(EnumType.STRING)
    private StatusRequest status;

    public Participation(Long id, LocalDateTime created, StatusRequest status) {
        this.id = id;
        this.created = created;
        this.status = status;
    }


    public Participation(LocalDateTime created, User requester, Event event, StatusRequest status) {
        this.created = created;
        this.event = event;
        this.requester = requester;
        this.status = status;
    }
}