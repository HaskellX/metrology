package ru.metrology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.metrology.dto.AuthorDto;
import ru.metrology.dto.IdDto;
import ru.metrology.dto.TrackDto;
import ru.metrology.model.Album;
import ru.metrology.model.Author;
import ru.metrology.model.Track;
import ru.metrology.repository.AuthorDao;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorDao authorDao;

    @Autowired
    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AuthorDto save(@RequestBody @Valid @NotNull AuthorDto authorDto) {
        Author author = new Author(authorDto.getName(), authorDto.getBirthday(), authorDto.getBirthPlace(),
                authorDto.getGenre(), authorDto.getAlias());
        authorDao.saveAndFlush(author);

        authorDto.setId(author.getId());
        return authorDto;
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody @Valid @NotNull IdDto idDto) {
        Author author = authorDao.getOne(idDto.getId());
        authorDao.delete(author);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorDto get(@RequestParam String alias) {
        Author author = authorDao.getByAlias(alias);

        return new AuthorDto(author);
    }
}
