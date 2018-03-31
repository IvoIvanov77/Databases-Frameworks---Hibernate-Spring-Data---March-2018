package app.servises;

import app.models.Author;
import app.models.Book;
import app.repositories.AuthorRepository;
import app.repositories.BookRepository;
import app.servises.contracts.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Author> findAuthorsWithBookReleasedBefore1990() throws ParseException {
        List<Author> allAuthors = this.findAll();
        Date date = new SimpleDateFormat("Y").parse("1990");
        List<Author> result = new ArrayList<>();

        for (Author author : allAuthors) {
            List<Book> books = this.bookRepository.findByReleaseDateBeforeAndAuthor(date, author);
            if(!books.isEmpty()){
                result.add(author);
            }
        }

        return result;
    }

    @Override
    public List<Author> findAllOrderedByBookCount() {
        return this.findAll().stream()
                .sorted(Comparator.comparing(Author::getNumberOfBooks).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Author findById(Integer id) {
        return this.authorRepository.findOne(id);
    }

    @Override
    public void remove(Author author) {
        this.authorRepository.delete(author);
    }

    @Override
    public List<Author> findAll() {
        return  this.authorRepository.findAll();
    }

    @Override
    public void save(Author author) {
        this.authorRepository.save(author);
    }

    @Override
    public String authorsWhoseFirstNameEndWith(String pattern){
        return this.getResult(this.authorRepository
                .findAuthorByFirstNameEndsWith(pattern)
                .map(Author::toString)
        );
    }

    @Override
    public String totalNumberOfCopiesByAuthor(){
        return this.getResult(this.authorRepository
                .totalNumberOfCopiesByAuthor()
                .map(ob -> String.format("%s - %s", ob[0], ob[1]))
        );
    }

    private String getResult(Stream<String> stream){
        StringBuilder builder = new StringBuilder();
        stream.forEach(s -> builder.append(s).append(System.lineSeparator()));
        return builder.toString().trim();
    }

}
