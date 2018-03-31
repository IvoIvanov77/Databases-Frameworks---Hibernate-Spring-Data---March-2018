package app.repositories;

import app.models.Author;
import app.models.Book;
import app.models.enums.AgeRestriction;
import app.models.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


    Stream<Book> findByAgeRestriction(AgeRestriction ageRestriction);

    Stream<Book> findByEditionTypeAndCopiesLessThan(
            EditionType editionType, int copies);

    Stream<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal upper, BigDecimal lower);

    @Query("select b from Book as b where SUBSTRING(b.releaseDate,1,4) not in :date")
    Stream<Book> findByReleaseDateNotIn(@Param(value = "date") String releaseDate);

    @Query("select b from Book as b where year(b.releaseDate) != :year")
    Stream<Book> findBooksNotReleaseOn(@Param("year") int year);

    Stream<Book> findByReleaseDateBefore(Date date);

    Stream<Book> findBooksByTitleContainsIgnoreCase(String title);

    Stream<Book> findBookByAuthorLastNameStartsWith(String pattern);


    @Query("select count(b) from Book as b where length(b.author.lastName) > :number")
    int numberOfBooksWithAuthorLastNameLongerThan(@Param(value = "number") int number);

    @Query("select count(b) from Book as b where length(b.title) > :number")
    int numberOfBooksWithTitlesLongerThan(@Param(value = "number") int number);

//    title, edition type, age restriction and price
    @Query("select b.title, b.editionType, b.ageRestriction, b.price" +
            " from Book as b where b.title = :title")
    Stream<Object[]> reducedBook(@Param(value = "title") String title);

    @Modifying
    @Transactional
    @Query("update Book b set b.copies = b.copies + :number where b.releaseDate > :date")
    int increaseBookCopiesReleasedAfter(@Param("date") Date date, @Param("number") int number);

    @Transactional
    int deleteBookByCopiesLessThan(int copies);

    List<Book> findByReleaseDateAfter(Date date);

    List<Book> findByReleaseDateBeforeAndAuthor(Date date, Author author);

    @Procedure(name = "countOfBooks")
    Integer numberOfBooksByAuthorName(@Param("name") String fullName);

    @Procedure(procedureName = "total_number_of_books_that_author_has_written")
    Integer totalNumberByAuthor(String authorFullName);




}
