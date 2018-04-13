package app.controllers;

import app.domain.xml_dto.p00_import_data.WrapperImportCustomerDto;
import app.domain.xml_dto.p01_ordered_customers.WrapperOrderedCustomers;
import app.domain.xml_dto.p05_total_sales_by_customer.WrapperCustomerStatistic;
import app.services.contracts.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component
public class CustomerController extends BaseController{

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public String importFromXmlString(String xmlString) {
        WrapperImportCustomerDto customersDto =
                null;
        try {
            customersDto = super.xmlParser.importXml(WrapperImportCustomerDto.class, xmlString);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        this.customerService.create(customersDto);
        return "Successfully import customers!";
    }



    public String orderedCustomers(){
        WrapperOrderedCustomers orderedCustomers = this.customerService.getOrderedCustomers();
        String xmlString = null;
        try {
            xmlString = this.xmlParser.exportXml(orderedCustomers);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xmlString;
    }

    public String totalSalesByCustomer(){
        WrapperCustomerStatistic customersStatistic = this.customerService.getTotalSalesByCustomer();
        String xmlString = null;
        try {
            xmlString = this.xmlParser.exportXml(customersStatistic);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xmlString;
    }

}
