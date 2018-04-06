package app.services.contracts;

import app.domain.dto.json.p00_seed_database.ImportProductJsonDto;
import app.domain.dto.json.p01_products_in_range.ExportProductInRangeDto;
import app.domain.models.Category;
import app.domain.models.Product;
import app.domain.models.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ProductService {

    void create(ImportProductJsonDto dto);

    List<ExportProductInRangeDto> productsInRage(BigDecimal low, BigDecimal high);

    List<Product> productsWithBuyerBySeller(User user);

    List<Product> productsBySeller(User user);

    Set<Product> findByCategory(Category category);
}
