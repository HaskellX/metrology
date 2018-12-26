package ru.metrology.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @Column(nullable = false)
    @Getter @Setter
    private String name;

    @Column(nullable = false)
    @Getter @Setter
    private LocalDate birthday;

    @Getter @Setter
    private String birthPlace;

    @Column(nullable = false)
    @Getter @Setter
    private String genre;

    @Getter @Setter
    private String alias;

    @OneToMany(mappedBy = "author")
    @Getter @Setter
    private List<Album> albums;

    public Author() {
    }

    public Author(String name, LocalDate birthday, String birthPlace, String genre, String alias) {
        this.name = name;
        this.birthday = birthday;
        this.birthPlace = birthPlace;
        this.genre = genre;
        this.alias = alias;
    }
}
