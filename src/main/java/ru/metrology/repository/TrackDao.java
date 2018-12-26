package ru.metrology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.metrology.model.Track;

@Repository
public interface TrackDao extends JpaRepository<Track, Long> {

    @Query
    Track getByTitle(String title);

    @Query
    Track getByAlbum_Id(Long albumId);

    @Query
    Track getByAlbum_Author_Id(Long authorId);
}
