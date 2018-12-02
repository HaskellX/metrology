package DAO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue
    private long id;
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }

    public ArrayList<Track> getTracksList() {
        return tracksList;
    }

    public void setTracksList(ArrayList<Track> tracksList) {
        this.tracksList = tracksList;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    private Date releaseDate;
    private boolean isSingle;
    private boolean isCollection;

    @ManyToMany
    private ArrayList<Track> tracksList;
    private String Genre;

}
