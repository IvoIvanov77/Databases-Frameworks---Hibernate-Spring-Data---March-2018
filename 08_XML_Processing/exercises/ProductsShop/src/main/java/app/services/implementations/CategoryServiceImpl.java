package app.services.implementations;

import app.domain.dto.p00_seed_database.WrapperCategoriesImportDto;
import app.domain.dto.p03_categories_by_products_count.WrapperCategoryStatDto;
import app.domain.models.Category;
import app.repositories.CategoryRepository;
import app.services.contracts.CategoryService;
import app.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void create(WrapperCategoriesImportDto dtos) {
        List<Category> categoriesToImport = dtos.getCategories()
                .stream()
                .map(dto -> ModelParser.getInstance().map(dto, Category.class))
                .collect(Collectors.toList());
        this.categoryRepository.save(categoriesToImport);
    }


    @Override
    public WrapperCategoryStatDto getAllOderByProductsCountDesc(){
        WrapperCategoryStatDto categoryStatDtos = new WrapperCategoryStatDto();
        categoryStatDtos.setCategories(this.categoryRepository.categoriesByProductsCount());
        return categoryStatDtos;
    }


}

