package app.repositories;

import app.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {


    @Query("select distinct a from Author a left join fetch a.books")
    List<Author> findAllWithBooks();
}
