package ru.metrology.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import ru.metrology.model.Track;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class TrackDto implements Serializable {

    @Getter @Setter
    private Long id;

    @NotNull
    @Getter @Setter
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Getter @Setter
    private LocalDate created;

    @Getter @Setter
    private Integer duration;

    @NotNull
    @Getter @Setter
    private Long album;

    public TrackDto() {
    }

    public TrackDto(Track track) {
        this.id = track.getId();
        this.title = track.getTitle();
        this.created = track.getCreated();
        this.duration = track.getDuration();
        this.album = track.getAlbum().getId();
    }
}
