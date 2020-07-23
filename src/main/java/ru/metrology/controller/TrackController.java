package ru.metrology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.metrology.dto.IdDto;
import ru.metrology.dto.TrackDto;
import ru.metrology.model.Album;
import ru.metrology.model.Track;
import ru.metrology.repository.AlbumDao;
import ru.metrology.repository.TrackDao;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/track")
public class TrackController {

    private final TrackDao trackDao;
    private final AlbumDao albumDao;

    @Autowired
    public TrackController(TrackDao trackDao, AlbumDao albumDao) {
        this.trackDao = trackDao;
        this.albumDao = albumDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TrackDto save(@RequestBody @Valid @NotNull TrackDto trackDto) {
        Album album = albumDao.getOne(trackDto.getAlbum());

        Track track = new Track(trackDto.getTitle(), trackDto.getCreated(), trackDto.getDuration(), album);
        trackDao.saveAndFlush(track);

        trackDto.setId(track.getId());
        return trackDto;
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody @Valid @NotNull IdDto idDto) {
        Track track = trackDao.getOne(idDto.getId());
        trackDao.delete(track);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TrackDto get(@RequestParam(required = false) Long id,
                            @RequestParam(required = false) String title,
                            @RequestParam(required = false) Long albumId,
                            @RequestParam(required = false) Long authorId) {
        Track track;
        if(id != null) {
            track = trackDao.getOne(id);
        } else if(title != null) {
            track = trackDao.getByTitle(title);
        } else if(albumId != null) {
            track = trackDao.getByAlbum_Id(albumId);
        } else {
            track = trackDao.getByAlbum_Author_Id(authorId);
        }

        return track != null ? new TrackDto(track) : null;
    }

}
