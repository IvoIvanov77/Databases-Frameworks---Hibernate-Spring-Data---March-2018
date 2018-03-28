package app.repositories;

import app.models.Author;
import app.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByReleaseDateAfter(Date date);

    List<Book> findByReleaseDateBeforeAndAuthor(Date date, Author author);




}
