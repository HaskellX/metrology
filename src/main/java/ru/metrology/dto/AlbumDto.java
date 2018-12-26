package ru.metrology.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.metrology.model.Album;
import ru.metrology.model.Author;
import ru.metrology.model.Track;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class AlbumDto implements Serializable {

    @Getter @Setter
    private Long id;

    @NotNull
    @Getter @Setter
    private String title;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Getter @Setter
    private LocalDate releaseDate;

    @NotNull
    @Getter @Setter
    private boolean single;

    @NotNull
    @Getter @Setter
    private boolean collection;

    @NotNull
    @Getter @Setter
    private Long author;

    @Getter @Setter
    private String genre;

    public AlbumDto() {
    }

    public AlbumDto(Album album) {
        this.id = album.getId();
        this.title = album.getTitle();
        this.releaseDate = album.getReleaseDate();
        this.single = album.isSingle();
        this.collection = album.isCollection();
        this.author = album.getAuthor().getId();
        this.genre = album.getGenre();
    }
}
