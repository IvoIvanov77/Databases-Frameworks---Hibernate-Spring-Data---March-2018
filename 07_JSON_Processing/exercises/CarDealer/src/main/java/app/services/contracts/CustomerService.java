package app.services.contracts;

import app.domain.json_dto.p05_total_sales_by_customer.CustomerStatisticDto;
import app.domain.json_dto.p00_import_data.ImportCustomerFromJsonDto;
import app.domain.json_dto.p01_ordered_customers.OrderedCustomerDto;

import java.util.List;

public interface CustomerService {


    void create(ImportCustomerFromJsonDto[] customersDto);


    List<OrderedCustomerDto> getOrderedCustomers();


    List<CustomerStatisticDto> getTotalSalesByCustomer();
}
