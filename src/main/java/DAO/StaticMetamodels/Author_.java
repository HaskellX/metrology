package DAO.StaticMetamodels;

import DAO.Author;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@StaticMetamodel(Author.class)
public class Author_ {
    private String FIO;
    private Date BirthDate;
    private String PlaceBirth;
    private String Genre;



}
