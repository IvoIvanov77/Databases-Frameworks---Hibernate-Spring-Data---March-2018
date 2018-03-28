package app;


import app.models.Author;
import app.models.Book;
import app.models.Category;
import app.models.enums.AgeRestriction;
import app.models.enums.EditionType;
import app.servises.contracts.AuthorService;
import app.servises.contracts.BookService;
import app.servises.contracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private AuthorService authorService;
    private BookService bookService;
    private CategoryService categoryService;


    @Autowired
    public ConsoleRunner(AuthorService authorService,
                         BookService bookService,
                         CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;

    }

    @Override
    public void run(String... strings) throws Exception {
//        seedDatabase();
//        this.q1_printBookTitlesAfter2000Year();
//        this.q2_printAllAuthorsWithAtLeastOneBookWithReleaseDateBefore1990();
//        this.q3_printAllAuthorsOrderedByNumberOfTheirBooksDescending();
        this.q4_printAllBooksFromAuthorGeorgePowell();
    }

    private void q1_printBookTitlesAfter2000Year() throws ParseException {
        for (String book : this.bookService.findAllBookTitlesAfter2000Year()) {
            System.out.println(book);
        }
    }

    private void q2_printAllAuthorsWithAtLeastOneBookWithReleaseDateBefore1990() throws ParseException {

        for (Author author : this.authorService.findAuthorsWithBookReleasedBefore1990()) {
            System.out.println(String.format("%s %s", author.getFirstName(), author.getLastName()));
        }
    }

    private void q3_printAllAuthorsOrderedByNumberOfTheirBooksDescending(){
        for (Author author : this.authorService.findAllOrderedByBookCount()) {
            System.out.println(String.format("%s %s %d",
                    author.getFirstName(), author.getLastName(), author.getNumberOfBooks()));
        }
    }

    private void q4_printAllBooksFromAuthorGeorgePowell(){
        for (Book book : this.bookService.findAllByGeorgePowell()) {
            System.out.println(String.format("%s %s %d",
                    book.getTitle(), book.getReleaseDate(), book.getCopies()));
        }
    }

    private void seedDatabase() throws IOException, ParseException {

        List<Author> authors = new ArrayList<>();

        BufferedReader authorsReader = new BufferedReader(new FileReader("src/authors.txt"));
        String line = authorsReader.readLine();
        while ((line = authorsReader.readLine()) != null) {
            String[] data = line.split("\\s+");
            String firstName = data[0];
            String lastName = data[1];

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);

            authors.add(author);

            this.authorService.save(author);
        }

        List<Category> categories = new ArrayList<>();

        BufferedReader categoriesReader = new BufferedReader(new FileReader("src/categories.txt"));
        while ((line = categoriesReader.readLine()) != null) {
            Category category = new Category();
            category.setName(line);

            categories.add(category);

            this.categoryService.save(category);
        }

        Random random = new Random();

        BufferedReader booksReader = new BufferedReader(new FileReader("src/books.txt"));
        line = booksReader.readLine();
        while((line = booksReader.readLine()) != null){
            String[] data = line.split("\\s+");

            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);

            Set<Category> bookCategories = new HashSet<>();

            for (int i = 0; i < random.nextInt(categories.size()); i++) {
                int categoryIndex = random.nextInt(categories.size());
                bookCategories.add(categories.get(categoryIndex));
            }
            book.setCategories(bookCategories);

            this.bookService.save(book);
        }
    }
}
