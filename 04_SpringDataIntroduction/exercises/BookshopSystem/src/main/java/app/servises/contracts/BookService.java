package app.servises.contracts;

import app.models.Book;

import java.text.ParseException;
import java.util.List;

public interface BookService extends BaseService<Book, Integer> {

    List<String> findAllBookTitlesAfter2000Year() throws ParseException;
    List<Book> findAllByGeorgePowell();


}
