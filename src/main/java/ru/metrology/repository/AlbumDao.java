package ru.metrology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.metrology.model.Album;
import ru.metrology.model.Track;

@Repository
public interface AlbumDao extends JpaRepository<Album, Long> {

    @Query
    Album getByTitle(String title);

    @Query
    Album getByAuthor_Id(Long authorId);

}
