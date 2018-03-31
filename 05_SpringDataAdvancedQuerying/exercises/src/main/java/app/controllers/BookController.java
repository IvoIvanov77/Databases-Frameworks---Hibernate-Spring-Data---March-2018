package app.controllers;


import app.models.dto.ReducedBook;
import app.io.Rider;
import app.models.Book;
import app.models.enums.AgeRestriction;
import app.models.enums.EditionType;
import app.servises.contracts.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {

    private final Rider rider;

    private final BookService bookService;

    private DateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");

    @Autowired
    public BookController(Rider rider, BookService bookService) {
        this.rider = rider;
        this.bookService = bookService;
    }

    public List<Book> getBooks(String fileName) throws IOException {

        return this.rider.read(fileName).stream().skip(1)
                .map(s -> {
                    Book book = new Book();
                    String[] input = s.split("\\s+", 6);
                    EditionType editionType = EditionType.values()[Integer.parseInt(input[0])];
                    Date releaseDate = null;
                    try {
                        releaseDate = this.dateFormat.parse(input[1]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    int copies = Integer.parseInt(input[2]);
                    BigDecimal price = new BigDecimal(input[3]);
                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(input[4])];
                    String title = input[5];
                    book.setEditionType(editionType);
                    book.setReleaseDate(releaseDate);
                    book.setCopies(copies);
                    book.setPrice(price);
                    book.setAgeRestriction(ageRestriction);
                    book.setTitle(title);

                    return book;
                }).collect(Collectors.toList());
    }

    @Transactional
    public void importBooks(List<Book> books){
        for (Book book : books) {
            this.bookService.save(book);
        }
    }

    public String booksTitlesByAgeRestriction(String restriction){
        return this.bookService.booksTitlesByAgeRestriction(restriction);
    }

    public String bookTitlesWithGoldenEditionAntLessThan5000Copies(){
       return this.bookService.bookTitlesWithGoldenEditionAntLessThan5000Copies();
    }

    public String booksWithPriceLowerThanAndHigherThan(String upper,String lower){
        return this.bookService.booksWithPriceLowerThanAndHigherThan(upper, lower);
    }

    public String booksWithReleaseYearNotIn(String year) {
        return this.bookService.booksWithReleaseYearNotIn(year);
    }

    public String booksReleasedBeforeDate(String date)  {
        try {
            return this.bookService.booksReleasedBeforeDate(date);
        } catch (ParseException e) {
            return e.getMessage();
        }
    }

    public String booksWithTitleContains(String input)  {
        return this.bookService.booksWithTitleContains(input);
    }

    public String booksWithAuthorLastNameStartsWith(String pattern){
        return this.bookService.booksWithAuthorLastNameStartsWith(pattern);
    }

    public String countOfBooksWithTitleLengthLongerThan(int number){
        return this.bookService.countOfBooksWithTitleLengthLongerThan(number);
    }

    public String reduceBook(String title){
        List<ReducedBook> reducedBooks = this.bookService.reduceBook(title);
        StringBuilder builder = new StringBuilder();
        for (ReducedBook reducedBook : reducedBooks) {
            builder.append(String.format("%s, %s, %s, %s",
                    reducedBook.getTitle(),
                    reducedBook.getEditionType(),
                    reducedBook.getRestrictionType(),
                    reducedBook.getPrice()))
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    public String increaseBookCopies(String date, String copies)  {
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date after = null;
        try {
            after = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int number = Integer.parseInt(copies);
        return this.bookService.increaseBookCopies(after, number);
    }

    public String numberOfBooksByAuthorFullName(String fullName){
        Integer countOfBooks = this.bookService.numberOfBooksByAuthor(fullName);
        switch (countOfBooks){
            case 0 : return String.format("%s has not written any books yet", fullName);
            case 1 : return String.format("%s has written 1 book", fullName);
            default:
                return String.format("%s has written %d book", fullName, countOfBooks);
        }

    }

    public String removeBooksWithCopiesCount(String input){
        int count = Integer.parseInt(input);
        return this.bookService.removeBooksWithCopiesCount(count);
    }


}
