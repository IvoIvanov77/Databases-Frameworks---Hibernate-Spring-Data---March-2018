package app.services.contracts;

import app.domain.dto.json.p00_seed_database.ImportCategoryJsonDto;
import app.domain.dto.json.p03_categories_by_products_count.CategoryStatDto;
import app.domain.models.Category;

import java.util.List;
import java.util.Set;


public interface CategoryService {

    void create(ImportCategoryJsonDto dto);

    void create(List<ImportCategoryJsonDto> dtos);

    Category findById(long id);

    List<CategoryStatDto> getAllOderByProductsCountDesc();
}
