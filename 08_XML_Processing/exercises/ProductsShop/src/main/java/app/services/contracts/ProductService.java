package app.services.contracts;

import app.domain.dto.p00_seed_database.WrapperProductsImportDto;
import app.domain.dto.p01_products_in_range.ExportProductInRangeDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void create(WrapperProductsImportDto dtos);

    List<ExportProductInRangeDto> productsInRage(BigDecimal low, BigDecimal high);

}
