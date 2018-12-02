package DAO.StaticMetamodels;

import DAO.Author;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@StaticMetamodel(Author.class)
public class Author_ {
    public static volatile SingularAttribute<Author, String> fio;
    public static volatile SingularAttribute<Author, String> placeBirth;
    public static volatile SingularAttribute<Author, Date> birthdate;
    public static volatile SingularAttribute<Author, String> genre;
    public static volatile SingularAttribute<Author, String> alias;
    public static volatile SingularAttribute<Author, Long> id;
}
