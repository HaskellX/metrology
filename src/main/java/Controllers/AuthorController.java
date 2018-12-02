package Controllers;

import Application.Application;
import DAO.Author;
import DAO.StaticMetamodels.Author_;
import DAO.StaticMetamodels.Track_;
import DAO.Track;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpRequest;
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
public class AuthorController {

    @PostMapping("/addauthor")
    public ResponseEntity AddTrack(Author author)
    {
        if(author == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);


        final Session session = Application.getSession();
        Transaction tr = null;
        try {
            Author newAuthor = author;
            tr = session.beginTransaction();
            session.save(newAuthor);
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
            Author author = (Author) session.get(Author.class, id);
            session.delete(author);
            tr.commit();
            return new ResponseEntity(HttpStatus.OK);
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
    }

    @GetMapping("getbyalias/{alias}")
    public ResponseEntity GetByAlias(String alias){
        if (alias == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        final Session session = Application.getSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Author> criteria = builder.createQuery(Author.class);
            Root<Author> root = criteria.from(Author.class);
            criteria.select(root);
            criteria.where(builder.like(root.get(Author_.alias), alias));
            Collection<Author> authors = session.createQuery(criteria).getResultList();
            tr.commit();
            return new ResponseEntity(authors, HttpStatus.OK);
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
}
