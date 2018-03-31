package app.servises.contracts;

import app.models.dto.ReducedBook;
import app.models.Book;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface BookService extends BaseService<Book, Integer> {

    List<String> findAllBookTitlesAfter2000Year() throws ParseException;
    List<Book> findAllByGeorgePowell();


    String booksTitlesByAgeRestriction(String restriction);

    String bookTitlesWithGoldenEditionAntLessThan5000Copies();

    String booksWithPriceLowerThanAndHigherThan(String upper, String lower);

    String booksWithReleaseYearNotIn(String year);

    String booksWithReleaseYearNotIn(int year);

    String booksReleasedBeforeDate(String date) throws ParseException;

    String booksWithTitleContains(String input);

    String booksWithAuthorLastNameStartsWith(String input);

    String countOfBooksWithTitleLengthLongerThan(int number);

    List<ReducedBook> reduceBook(String title);

    String increaseBookCopies(Date after, int number);

    String removeBooksWithCopiesCount(int count);

    Integer numberOfBooksByAuthor(String name);
}
