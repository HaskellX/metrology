package Controllers;

import Application.Application;
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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

@RestController
public class TrackController {

    @PostMapping("/addtrack")
    public ResponseEntity AddTrack(Track track) {
        if(track == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(track.getAuthor() == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(track.getCreationDate() < 1){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(track.getTitle() == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(track.getTitle() == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        final Session session = Application.getSession();
        Transaction tr = null;
        try {
            Track newTrack = new Track();
            tr = session.beginTransaction();
            session.save(newTrack);
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

    @RequestMapping("/deletetrack/{id}")
    public ResponseEntity DeleteTrack(long id) {

        if(id < 1) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        final Session session = Application.getSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Track newTrack = (Track)session.get(Track.class, id);
            session.delete(newTrack);
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


    @GetMapping("/getbyid/{id}")
    public ResponseEntity GetById(long id) {

        if(id < 1) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        final Session session = Application.getSession();
        try {
            Track newTrack = (Track)session.get(Track.class, id);
            return new ResponseEntity(newTrack, HttpStatus.OK);
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

    @GetMapping("/getbyname/{name}")
    public ResponseEntity GetByName(String name) {
        if (name == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        final Session session = Application.getSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Track> criteria = builder.createQuery(Track.class);
            Root<Track> root = criteria.from(Track.class);
            criteria.select(root);
            criteria.where(builder.like(root.get(Track_.title), name));
            Collection<Track> tracks = session.createQuery(criteria).getResultList();
            tr.commit();
            return new ResponseEntity(tracks, HttpStatus.OK);
        } catch (HibernateException ex) {
            if (tr != null) {
                tr.rollback();
            }
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } finally {
            session.close();
        }
    }

        @GetMapping("/getbyalbum/{id}")
        public ResponseEntity GetByAlbum(long id) {

            if (id < 1) return new ResponseEntity(HttpStatus.BAD_REQUEST);
            final Session session = Application.getSession();
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Track> criteria = builder.createQuery(Track.class);
                Root<Track> root = criteria.from(Track.class);
                criteria.select(root);
                criteria.where(builder.equal(root.get(Track_.album), id));
                Collection<Track> tracks = session.createQuery(criteria).getResultList();
                return new ResponseEntity(tracks, HttpStatus.OK);
            } catch (HibernateException ex) {
                ex.printStackTrace();
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } finally {
                session.close();
            }
        }

            @GetMapping("/getbyalbum/{id}")
            public ResponseEntity GetByAuthor(long id) {

                if (id < 1) return new ResponseEntity(HttpStatus.BAD_REQUEST);
                final Session session = Application.getSession();
                try {
                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    CriteriaQuery<Track> criteria = builder.createQuery(Track.class);
                    Root<Track> root = criteria.from(Track.class);
                    criteria.select(root);
                    criteria.where(builder.equal(root.get(Track_.author), id));
                    Collection<Track> tracks = session.createQuery(criteria).getResultList();
                    return new ResponseEntity(tracks, HttpStatus.OK);
                } catch (HibernateException ex) {
                    ex.printStackTrace();
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                } finally {
                    session.close();
                }
    }

}



