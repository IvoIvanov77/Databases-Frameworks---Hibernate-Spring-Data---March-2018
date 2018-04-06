package app.controllers;

import app.domain.json_dto.p00_import_data.ImportCustomerFromJsonDto;
import app.domain.json_dto.p01_ordered_customers.OrderedCustomerDto;
import app.domain.json_dto.p05_total_sales_by_customer.CustomerStatisticDto;
import app.services.contracts.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerController extends BaseController{

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public String importFromJsonString(String jsonString){
        ImportCustomerFromJsonDto[] customersDto =
                super.jsonParser.importJson(ImportCustomerFromJsonDto[].class, jsonString);
        this.customerService.create(customersDto);
        return "Successfully import customers!";
    }

    public String orderedCustomers(){
        List<OrderedCustomerDto> resultList = this.customerService.getOrderedCustomers();
        String jsonString = this.jsonParser.exportJson(resultList);
        return jsonString;
    }

    public String totalSalesByCustomer(){
        List<CustomerStatisticDto> resultList = this.customerService.getTotalSalesByCustomer();
        String jsonString = this.jsonParser.exportJson(resultList);
        return jsonString;
    }

}
