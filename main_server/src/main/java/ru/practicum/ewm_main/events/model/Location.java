package ru.practicum.ewm_main.events.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "locations")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private float lat;

    @Column(nullable = false)
    private float lon;

    public Location(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }
}