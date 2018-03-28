package app.servises.contracts;

import app.models.Author;

import java.text.ParseException;
import java.util.List;

public interface AuthorService extends BaseService<Author, Integer>{

    List<Author> findAuthorsWithBookReleasedBefore1990() throws ParseException;

    List<Author> findAllOrderedByBookCount();
}
