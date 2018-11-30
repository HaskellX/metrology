package DAO;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Album {
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
    private ArrayList<Track> tracksList;
    private String Genre;
}