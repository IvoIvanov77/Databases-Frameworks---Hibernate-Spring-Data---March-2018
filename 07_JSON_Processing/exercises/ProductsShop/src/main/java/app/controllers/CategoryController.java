package app.controllers;

import app.domain.dto.json.p00_seed_database.ImportCategoryJsonDto;
import app.domain.dto.json.p03_categories_by_products_count.CategoryStatDto;
import app.io.JSONParser;
import app.services.contracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final JSONParser jsonParser;

    @Autowired
    public CategoryController(CategoryService categoryService, JSONParser jsonParser) {
        this.categoryService = categoryService;
        this.jsonParser = jsonParser;
    }

    public void importCategoriesFromJason(){
        ImportCategoryJsonDto[] categoryDtos = this.jsonParser.importJson(ImportCategoryJsonDto[].class,
                "categories.json");

        this.categoryService.create(Arrays.asList(categoryDtos));
    }

    public String categoriesByProductsCount(){
        List<CategoryStatDto> dtos =
                this.categoryService.getAllOderByProductsCountDesc();
        return this.jsonParser.exportJson(dtos);
    }
}
