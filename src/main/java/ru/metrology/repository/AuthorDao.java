package ru.metrology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.metrology.model.Author;

@Repository
public interface AuthorDao extends JpaRepository<Author, Long> {

    @Query
    Author getByAlias(String alias);
}
