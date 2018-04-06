package app.controllers;

import app.domain.dto.json.p00_seed_database.ImportProductJsonDto;
import app.domain.dto.json.p01_products_in_range.ExportProductInRangeDto;
import app.domain.models.Product;
import app.io.JSONParser;
import app.services.contracts.ProductService;
import org.hibernate.jpa.criteria.expression.function.UpperFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final JSONParser jsonParser;

    @Autowired
    public ProductController(ProductService productService, JSONParser jsonParser) {
        this.productService = productService;
        this.jsonParser = jsonParser;
    }


    public void importProductsFromJson(){

        ImportProductJsonDto[] productDtos = this.jsonParser
                .importJson(ImportProductJsonDto[].class, "products.json");

        for (ImportProductJsonDto productDto : productDtos) {
            this.productService.create(productDto);
        }

    }

    public String productsInRage(String lowerBoundPrice, String upperBoundPrice){
        List<ExportProductInRangeDto> dtos =
                this.productService.productsInRage(new BigDecimal(lowerBoundPrice),
                        new BigDecimal(upperBoundPrice));
        return this.jsonParser.exportJson(dtos);
    }
}
