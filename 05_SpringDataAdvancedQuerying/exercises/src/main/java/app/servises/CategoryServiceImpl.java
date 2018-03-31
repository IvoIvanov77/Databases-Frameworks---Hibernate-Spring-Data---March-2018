package app.servises;

import app.models.Category;
import app.repositories.CategoryRepository;
import app.servises.contracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(Integer id) {
        return null;
    }

    @Override
    public void remove(Category object) {

    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public void save(Category category) {
        this.categoryRepository.save(category);
    }
}
