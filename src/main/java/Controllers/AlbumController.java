package Controllers;

import Application.Application;
import DAO.Album;
import DAO.Author;
import DAO.StaticMetamodels.Album_;
import DAO.StaticMetamodels.Author_;
import DAO.StaticMetamodels.Track_;
import DAO.Track;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.JoinTable;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class AlbumController {
    @PostMapping("/addalbum")
    public ResponseEntity AddTrack(Album album) {
        final Session session = Application.getSession();
        Transaction tr = null;
        try {
            Album newAlbum = new Album();
            tr = session.beginTransaction();
            session.save(newAlbum);
            tr.commit();
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (HibernateException ex)
        {
            if (tr != null){
                tr.rollback();
            }
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.OK);
        }
        finally{
            session.close();
        }

    }

    @GetMapping("/deletealbum/{id}")
    public ResponseEntity DeleteTrack(long id) {
        if(id < 1) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        final Session session = Application.getSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Album album = (Album)session.get(Album.class, id);
            session.delete(album);
            tr.commit();
        }
        catch (HibernateException ex)
        {
            if (tr != null){
                tr.rollback();
            }
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        finally{
            session.close();
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/getalbumbyid/{id}")
    public ResponseEntity GetById(long id) {

        if(id < 1) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        final Session session = Application.getSession();
        try {
            Album newAlbum = (Album)session.get(Album.class, id);
            return new ResponseEntity(newAlbum, HttpStatus.OK);
        }
        catch (HibernateException ex)
        {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        finally{
            session.close();
        }
    }

    @GetMapping("/getalbumbyname/{name}")
    public ResponseEntity GetByName(String name) {
        if (name == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        final Session session = Application.getSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Album> criteria = builder.createQuery(Album.class);
            Root<Album> root = criteria.from(Album.class);
            criteria.select(root);
            criteria.where(builder.like(root.get(Album_.title), name));
            Collection<Album> albums = session.createQuery(criteria).getResultList();
            return new ResponseEntity(albums, HttpStatus.OK);
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } finally {
            session.close();
        }
    }

    @GetMapping("/getbytrack/{id}")
    public ResponseEntity GetByTrack(Track track) {
        if (track == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        final Session session = Application.getSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Track> criteria = builder.createQuery(Track.class);
            Root<Track> root = criteria.from(Track.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Track_.album), track.getId()));
            Collection<Track> tracks = session.createQuery(criteria).getResultList();
            return new ResponseEntity(tracks, HttpStatus.OK);
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } finally {
            session.close();
        }
    }

    @PostMapping("/getbyaauthorname")
    public ResponseEntity GetByAuthorName(Author author) {

        if (author == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        final Session session = Application.getSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Album> criteria = builder.createQuery(Album.class);
            Root<Album> mainTable = criteria.from(Album.class);
            Join<Album, Track> join = mainTable.join(Album_.tracksList, JoinType.INNER);
            Join<Track, Author> join2 = join.join(Track_.author, JoinType.INNER);
            criteria.select(mainTable);
            criteria.where(builder.equal(join2.get(Author_.fio), author.getFIO()));
            Collection<Album> tracks = session.createQuery(criteria).getResultList();
            return new ResponseEntity(tracks, HttpStatus.OK);
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } finally {
            session.close();
        }
    }
}
