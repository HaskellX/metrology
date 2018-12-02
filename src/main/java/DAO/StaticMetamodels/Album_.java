package DAO.StaticMetamodels;

import DAO.Album;
import DAO.Track;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@StaticMetamodel(Album.class)
public class Album_ {
    public static volatile CollectionAttribute<Album, Track> tracksList;
    public static volatile SingularAttribute<Album, Boolean> isSingle;
    public static volatile SingularAttribute<Album, Date> releaseDate;
    public static volatile SingularAttribute<Album, Boolean> isCollection;
    public static volatile SingularAttribute<Album, String> genre;
    public static volatile SingularAttribute<Album, Long> id;
    public static volatile SingularAttribute<Album, String> title;
}
