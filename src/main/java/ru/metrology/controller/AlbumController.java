package ru.metrology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.metrology.dto.AlbumDto;
import ru.metrology.dto.AuthorDto;
import ru.metrology.dto.IdDto;
import ru.metrology.dto.TrackDto;
import ru.metrology.model.Album;
import ru.metrology.model.Author;
import ru.metrology.model.Track;
import ru.metrology.repository.AlbumDao;
import ru.metrology.repository.AuthorDao;
import ru.metrology.repository.TrackDao;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/album")
public class AlbumController {

    private final AlbumDao albumDao;
    private final AuthorDao authorDao;
    private final TrackDao trackDao;

    @Autowired
    public AlbumController(AlbumDao albumDao, AuthorDao authorDao, TrackDao trackDao) {
        this.albumDao = albumDao;
        this.authorDao = authorDao;
        this.trackDao = trackDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AlbumDto save(@RequestBody @Valid @NotNull AlbumDto albumDto) {
        Author author = authorDao.getOne(albumDto.getAuthor());

        Album album = new Album(albumDto.getTitle(), albumDto.getReleaseDate(), albumDto.isSingle(),
                albumDto.isCollection(), author, albumDto.getGenre());

        albumDao.saveAndFlush(album);

        albumDto.setId(album.getId());
        return albumDto;
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody @Valid @NotNull IdDto idDto) {
        Author author = authorDao.getOne(idDto.getId());

        authorDao.delete(author);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AlbumDto get(@RequestParam(required = false) Long id,
                        @RequestParam(required = false) String title,
                        @RequestParam(required = false) Long trackId,
                        @RequestParam(required = false) Long authorId) {
        Album album;
        if(id != null) {
            album = albumDao.getOne(id);
        } else if(title != null) {
            album = albumDao.getByTitle(title);
        } else if(trackId != null) {
            Track track = trackDao.getOne(trackId);
            album = track.getAlbum();
        } else {
            album = albumDao.getByAuthor_Id(authorId);
        }

        return album != null ? new AlbumDto(album) : null;
    }
}
