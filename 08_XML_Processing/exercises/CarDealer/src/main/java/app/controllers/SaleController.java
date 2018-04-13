package app.controllers;

import app.domain.xml_dto.p06_sales_with_applied_discount.WrapperCarWithDiscountDtos;
import app.io.contracts.Parser;
import app.services.contracts.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component
public class SaleController {

    private final SaleService saleService;
    private final Parser xmlParser;

    @Autowired
    public SaleController(SaleService saleService, Parser xmlParser) {
        this.saleService = saleService;
        this.xmlParser = xmlParser;
    }


    public String createSales(){
        this.saleService.createRandomSales();
        return "Successfully import sales!";
    }

    public String allSalesWithAppliedDiscount(){
        WrapperCarWithDiscountDtos salesDto = this.saleService.getSalesWithAppliedDiscount();
        String xmlString = null;
        try {
            xmlString = this.xmlParser.exportXml(salesDto);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xmlString;
    }

}
