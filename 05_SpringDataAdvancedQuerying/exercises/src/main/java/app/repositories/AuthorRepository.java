package app.repositories;

import app.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {


    Stream<Author> findAuthorByFirstNameEndsWith(String firstName);

    @Query("select b.author, sum(b.copies) as sc from Book as b group by b.author order by sc desc")
    Stream<Object[]> totalNumberOfCopiesByAuthor();



}
