package ru.metrology.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @Column(nullable = false)
    @Getter @Setter
    private String title;

    @Getter @Setter
    private LocalDate created;

    // in seconds
    @Getter @Setter
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "album")
    @Getter @Setter
    private Album album;

    public Track() {
    }

    public Track(String title, LocalDate created, Integer duration, Album album) {
        this.title = title;
        this.created = created;
        this.duration = duration;
        this.album = album;
    }
}
