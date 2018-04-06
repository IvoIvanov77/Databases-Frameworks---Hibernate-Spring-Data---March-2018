package app.controllers;

import app.domain.json_dto.p06_sales_with_applied_discount.SaleWithDiscountDto;
import app.io.JSONParser;
import app.services.contracts.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaleController {

    private final SaleService saleService;
    private final JSONParser jsonParser;

    @Autowired
    public SaleController(SaleService saleService, JSONParser jsonParser) {
        this.saleService = saleService;
        this.jsonParser = jsonParser;
    }


    public String createSales(){
        this.saleService.createRandomSales();
        return "Successfully import sales!";
    }

    public String allSalesWithAppliedDiscount(){
        List<SaleWithDiscountDto> resultList = this.saleService.getSalesWithAppliedDiscount();
        String jsonString = this.jsonParser.exportJson(resultList);
        return jsonString;
    }

}
