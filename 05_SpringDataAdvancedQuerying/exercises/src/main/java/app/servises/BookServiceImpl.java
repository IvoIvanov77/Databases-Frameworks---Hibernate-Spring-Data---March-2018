package app.servises;

import app.models.dto.ReducedBook;
import app.models.Book;
import app.models.enums.AgeRestriction;
import app.models.enums.EditionType;
import app.repositories.BookRepository;
import app.servises.contracts.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public String booksTitlesByAgeRestriction(String restriction){
        AgeRestriction ageRestriction = AgeRestriction.valueOf(restriction.toUpperCase());
        return this.getResult(
                this.bookRepository
                .findByAgeRestriction(ageRestriction)
                .map(Book::getTitle)
        );
    }

    @Override
    public String bookTitlesWithGoldenEditionAntLessThan5000Copies(){
        return this.getResult(
                this.bookRepository
                .findByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000)
                .map(Book::getTitle)
        );
    }

    @Override
    public String booksWithPriceLowerThanAndHigherThan(String upper,String lower){
        return this.getResult(
                this.bookRepository
                .findByPriceLessThanOrPriceGreaterThan(new BigDecimal(upper), new BigDecimal(lower))
                .map(book -> String.format("%s - $%.2f", book.getTitle(), book.getPrice()))
        );
    }

    @Override
    public String booksWithReleaseYearNotIn(String year) {
        return this.getResult(this.bookRepository
                .findByReleaseDateNotIn(year)
                .map(Book::getTitle)

        );
    }

    @Override
    public String booksWithReleaseYearNotIn(int year) {
        return this.getResult(this.bookRepository
                .findBooksNotReleaseOn(year)
                .map(Book::getTitle)
        );
    }

    @Override
    public String booksReleasedBeforeDate(String input) throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(input);
        return this.getResult(this.bookRepository
                .findByReleaseDateBefore(date)
                .map(Book::getTitle)
        );
    }

    @Override
    public String booksWithTitleContains(String input)  {
        return this.getResult(this.bookRepository
                .findBooksByTitleContainsIgnoreCase(input)
                .map(Book::getTitle)
        );
    }

    @Override
    public String booksWithAuthorLastNameStartsWith(String input)  {
        return this.getResult(this.bookRepository
                .findBookByAuthorLastNameStartsWith(input)
                .map(book -> String.format("%s (%s %s)", book.getTitle(),
                        book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
        );
    }

    @Override
    public String countOfBooksWithTitleLengthLongerThan(int number){
        int count = this.bookRepository.numberOfBooksWithTitlesLongerThan(number);
        return String.format("There are %d books with longer title than %d symbols", count, number);
    }

    @Override
    public List<ReducedBook> reduceBook(String title){

        List<ReducedBook> reducedBooks = this.bookRepository.reducedBook(title)
                .map(ob -> new ReducedBook() {
                    @Override
                    public String getTitle() {
                        return ob[0].toString();
                    }

                    @Override
                    public String getEditionType() {
                        return ob[1].toString();
                    }

                    @Override
                    public String getRestrictionType() {
                        return ob[2].toString();
                    }

                    @Override
                    public String getPrice() {
                        return ob[3].toString();
                    }
                }).collect(Collectors.toList());
//        return this.getResult(this.bookRepository
//                .reducedBook(title)
//                .map(ob -> String.format("%s %s %s %s", ob[0], ob[1], ob[2], ob[3]))
//        );
        return reducedBooks;
    }

    @Override
    public String increaseBookCopies(Date after, int number)  {
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        int foundBooks = this.bookRepository.increaseBookCopiesReleasedAfter(after, number);
        return String.format("%s books are released after %s so total of %d book copies were added",
                foundBooks, dateFormat.format(after), foundBooks * number);
    }

    @Override
    public String removeBooksWithCopiesCount(int count) {
        int deletesBook = this.bookRepository.deleteBookByCopiesLessThan(count);
        return String.format("%d books were deleted", deletesBook);
    }

    @Override
    public Integer numberOfBooksByAuthor(String name){
        return this.bookRepository.totalNumberByAuthor(name);
    }


    private String getResult(Stream<String> stream){
        StringBuilder builder = new StringBuilder();
        stream.forEach(s -> builder.append(s).append(System.lineSeparator()));
        return builder.toString().trim();
    }


}
