package ru.metrology.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.metrology.model.Author;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class AuthorDto implements Serializable {

    @Getter @Setter
    private Long id;

    @NotNull
    @Getter @Setter
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Getter @Setter
    private LocalDate birthday;

    @Getter @Setter
    private String birthPlace;

    @NotNull
    @Getter @Setter
    private String genre;

    @Getter @Setter
    private String alias;

    public AuthorDto() {
    }

    public AuthorDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.birthday = author.getBirthday();
        this.birthPlace = author.getBirthPlace();
        this.genre = author.getGenre();
        this.alias = author.getAlias();
    }
}
