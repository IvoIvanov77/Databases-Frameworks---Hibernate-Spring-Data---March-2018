package app.services.contracts;

import app.domain.dto.p00_seed_database.ImportCategoryXmlDto;
import app.domain.dto.p00_seed_database.WrapperCategoriesImportDto;
import app.domain.dto.p03_categories_by_products_count.WrapperCategoryStatDto;
import app.domain.models.Category;

import java.util.List;


public interface CategoryService {

    void create(WrapperCategoriesImportDto dtos);

    WrapperCategoryStatDto getAllOderByProductsCountDesc();
}
