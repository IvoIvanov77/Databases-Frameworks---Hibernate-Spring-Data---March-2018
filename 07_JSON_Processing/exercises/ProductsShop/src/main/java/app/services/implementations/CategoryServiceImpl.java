package app.services.implementations;

import app.domain.dto.json.p00_seed_database.ImportCategoryJsonDto;
import app.domain.dto.json.p03_categories_by_products_count.CategoryStatDto;
import app.domain.models.Category;
import app.repositories.CategoryRepository;
import app.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.services.contracts.CategoryService;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void create(ImportCategoryJsonDto dto) {
        Category category = Mapper.getInstance().map(dto, Category.class);
        this.categoryRepository.save(category);

    }

    @Override
    public void create(List<ImportCategoryJsonDto> categoriesDtos) {

        List<Category> categories = categoriesDtos.stream()
                .map(dto -> Mapper.getInstance().map(dto, Category.class))
                .collect(Collectors.toList());
        this.categoryRepository.save(categories);
    }

    @Override
    public Category findById(long id) {
        return this.categoryRepository.findOne(id);
    }



    @Override
    public List<CategoryStatDto> getAllOderByProductsCountDesc(){
        return this.categoryRepository.categoriesByProductsCount();
    }


}

