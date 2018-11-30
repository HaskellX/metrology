package DAO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
public class Track {


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

    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public long getAlbum() {
        return album;
    }

    public void setAlbum(long album) {
        this.album = album;
    }


    private long album;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private long author;
    private int creationDate;

}

