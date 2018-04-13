package app.controllers;

import app.domain.dto.p00_seed_database.WrapperCategoriesImportDto;
import app.domain.dto.p03_categories_by_products_count.WrapperCategoryStatDto;
import app.services.contracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class CategoryController extends BaseController{

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String importFromXmlString(String xmlString) {
        WrapperCategoriesImportDto categoriesImportDto = null;
        try {
            categoriesImportDto = this.xmlParser.importXml(WrapperCategoriesImportDto.class, xmlString);
            this.categoryService.create(categoriesImportDto);
            return "Successfully import categories!";
        } catch (IOException | JAXBException e) {
            return e.getMessage();
        }
    }

    public String categoriesByProductsCount(){
        WrapperCategoryStatDto dtos =
                this.categoryService.getAllOderByProductsCountDesc();
        try {
            return this.xmlParser.exportXml(dtos);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
