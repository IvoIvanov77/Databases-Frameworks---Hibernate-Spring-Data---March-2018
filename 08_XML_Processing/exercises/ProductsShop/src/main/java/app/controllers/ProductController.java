package app.controllers;

import app.domain.dto.p00_seed_database.WrapperProductsImportDto;
import app.domain.dto.p01_products_in_range.ExportProductInRangeDto;
import app.domain.dto.p01_products_in_range.WrapperProductsDto;
import app.services.contracts.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductController extends BaseController{

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String importFromXmlString(String xmlString) {
        WrapperProductsImportDto productsImportDto = null;
        try {
            productsImportDto = this.xmlParser.importXml(WrapperProductsImportDto.class, xmlString);
            this.productService.create(productsImportDto);
            return "Successfully import products!";
        } catch (IOException | JAXBException e) {
            return e.getMessage();
        }
    }

    public String productsInRage(String lowerBoundPrice, String upperBoundPrice){
        List<ExportProductInRangeDto> dtos =
                this.productService.productsInRage(new BigDecimal(lowerBoundPrice),
                        new BigDecimal(upperBoundPrice));
        WrapperProductsDto wrapperProductsImportDto = new WrapperProductsDto();
        wrapperProductsImportDto.setProducts(dtos);
        String content = null;
        try {
            content = this.xmlParser.exportXml(wrapperProductsImportDto);
        } catch (IOException | JAXBException e) {
            content = e.getMessage();
        }
        return content;
    }
}
