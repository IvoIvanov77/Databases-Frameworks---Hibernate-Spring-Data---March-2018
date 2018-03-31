package app.controllers;

import app.io.Rider;
import app.models.Category;
import app.servises.contracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CategoryController {

    private final Rider rider;

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(Rider rider, CategoryService categoryService) {
        this.rider = rider;
        this.categoryService = categoryService;
    }


    public List<Category> gerCategories(String fileName) throws IOException {
        return this.rider.read(fileName).stream()
                .map(s -> {
                    Category category = new Category();
                    category.setName(s);
                    return category;
                }).collect(Collectors.toList());

    }
}
