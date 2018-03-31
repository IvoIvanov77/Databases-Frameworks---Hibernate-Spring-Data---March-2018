package app.terminal;


import app.controllers.AuthorController;
import app.controllers.BookController;
import app.controllers.CategoryController;
import app.models.Author;
import app.models.Book;
import app.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


@Component
public class ConsoleRunner implements CommandLineRunner {


    private final AuthorController authorController;
    private final CategoryController categoryController;
    private final BookController bookController;

    @Autowired
    public ConsoleRunner(AuthorController authorController, CategoryController categoryController, BookController bookController) {
        this.authorController = authorController;
        this.categoryController = categoryController;
        this.bookController = bookController;
    }



    @Override
    public void run(String... strings) throws Exception {
        seedDatabase();
//        this.p01_booksTitlesByAgeRestriction("mInOr");
//        this.p02_goldenBooks();
//        this.p03_booksByPrice();
//        this.p04_notReleasedBooks("1998");
//        this.p05_booksReleasedBeforeDate("12-04-1992");
//        this.p06_authorsSearch("dy");
//        this.p07_bookSearch("WOR");
//        this.p08_bookTitlesSearch("gr");
//        this.p09_countBooks(40);
//        this.p10_totalBookCopies();
//        this.p11_reducedBook("Absalom");
//        this.p12_increaseBookCopies("12 Oct 2005", "100");
//        this.p13_removeBooks("4200");
        this.p14_storedProcedure("Christina Jordan");

    }

    private void p01_booksTitlesByAgeRestriction(String restriction){
        System.out.println(this.bookController.booksTitlesByAgeRestriction(restriction));
    }

    private void p02_goldenBooks(){
        System.out.println(this.bookController.bookTitlesWithGoldenEditionAntLessThan5000Copies());
    }

    private void p03_booksByPrice(){
        System.out.println(this.bookController.booksWithPriceLowerThanAndHigherThan("5","40"));
    }

    private void p04_notReleasedBooks(String year){
        System.out.println(this.bookController.booksWithReleaseYearNotIn(year));
    }

    private void p05_booksReleasedBeforeDate(String date){
        System.out.println(this.bookController.booksReleasedBeforeDate(date));
    }

    private void p06_authorsSearch(String pattern){
        System.out.println(this.authorController.authorsWhoseFirstNameEndWith(pattern));
    }

    private void p07_bookSearch(String pattern){
        System.out.println(this.bookController.booksWithTitleContains(pattern));
    }

    private void p08_bookTitlesSearch(String pattern){
        System.out.println(this.bookController.booksWithAuthorLastNameStartsWith(pattern));
    }

    private void p09_countBooks(int number){
        System.out.println(this.bookController.countOfBooksWithTitleLengthLongerThan(number));
    }

    private void p10_totalBookCopies(){
        System.out.println(this.authorController.totalNumberOfCopiesByAuthor());
    }

    private void p11_reducedBook(String title){
        System.out.println(this.bookController.reduceBook(title));
    }

    private void p12_increaseBookCopies(String date, String number){
        System.out.println(this.bookController.increaseBookCopies(date, number));
    }

    private void p13_removeBooks(String count){
        System.out.println(this.bookController.removeBooksWithCopiesCount(count));
    }

    private void p14_storedProcedure(String authorFullName){
        System.out.println(this.bookController.numberOfBooksByAuthorFullName(authorFullName));
    }


    private void seedDatabase() throws IOException {
        List<Author> authors = this.authorController.getAuthors("/data/authors.txt");
        List<Category> categories = this.categoryController.gerCategories("/data/categories.txt");
        List<Book> books = this.bookController.getBooks("/data/books.txt");
        Random random = new Random();

        for (Book book : books) {
            book.setAuthor(authors.get(random.nextInt(authors.size())));
            int categoryCount = random.nextInt(categories.size());
            Set<Category> bookCategories = new HashSet<>();
            for (int i = 0; i < categoryCount ; i++) {
                bookCategories.add(categories.get(random.nextInt(categories.size())));
            }
            book.setCategories(bookCategories);
        }

        this.bookController.importBooks(books);
    }
}
