package app.controllers;

import app.io.Rider;
import app.models.Author;
import app.servises.contracts.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthorController {

    private final Rider rider;

    private final AuthorService authorService;

    @Autowired
    public AuthorController(Rider rider, AuthorService authorService) {
        this.rider = rider;
        this.authorService = authorService;
    }

    public List<Author> getAuthors(String fileName) throws IOException {
       return this.rider.read(fileName).stream().skip(1)
               .map(s -> {
                   Author author = new Author();
                   String[] input = s.split("\\s+");
                   String firstName = input[0];
                   String lastName = input[1];
                   author.setFirstName(firstName);
                   author.setLastName(lastName);

                   return author;
               }).collect(Collectors.toList());
    }

    public String authorsWhoseFirstNameEndWith(String pattern){
       return this.authorService.authorsWhoseFirstNameEndWith(pattern);
    }

    public String totalNumberOfCopiesByAuthor(){
        return this.authorService.totalNumberOfCopiesByAuthor();
    }

}
