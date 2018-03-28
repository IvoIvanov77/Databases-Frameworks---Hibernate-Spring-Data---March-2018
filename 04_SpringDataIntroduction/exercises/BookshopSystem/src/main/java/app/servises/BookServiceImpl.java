package app.servises;

import app.models.Book;
import app.repositories.BookRepository;
import app.servises.contracts.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<String> findAllBookTitlesAfter2000Year() throws ParseException {
        List<Book> result = this.bookRepository.findByReleaseDateAfter(
                new SimpleDateFormat("Y").parse("2000"));

        return result.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByGeorgePowell() {
        return this.findAll().stream()
                .filter(book -> book.getAuthor().toString().equals("George Powell"))
                .sorted(Comparator.comparing(Book::getReleaseDate)
                        .reversed()
                        .thenComparing(Book::getTitle))
                .collect(Collectors.toList());

    }

    @Override
    public Book findById(Integer id) {
        return this.bookRepository.findOne(id);
    }

    @Override
    public void remove(Book book) {
        this.bookRepository.delete(book);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public void save(Book book) {
        this.bookRepository.save(book);
    }
}
