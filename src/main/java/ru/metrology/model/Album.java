package ru.metrology.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @Column(nullable = false)
    @Getter @Setter
    private String title;

    @Column(nullable = false)
    @Getter @Setter
    private LocalDate releaseDate;

    @Column(nullable = false)
    @Getter @Setter
    private boolean single;

    @Column(nullable = false)
    @Getter @Setter
    private boolean collection;

    @ManyToOne
    @JoinColumn(name = "author")
    @Getter @Setter
    private Author author;

    @OneToMany(mappedBy = "album")
    @Getter @Setter
    private List<Track> tracks;

    @Getter @Setter
    private String genre;

    public Album() {
    }

    public Album(String title, LocalDate releaseDate, boolean single, boolean collection, Author author, String genre) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.single = single;
        this.collection = collection;
        this.author = author;
        this.genre = genre;
    }
}
