package app.services.contracts;

import app.domain.xml_dto.p00_import_data.WrapperImportCustomerDto;
import app.domain.xml_dto.p01_ordered_customers.WrapperOrderedCustomers;
import app.domain.xml_dto.p05_total_sales_by_customer.WrapperCustomerStatistic;

public interface CustomerService {


    void create(WrapperImportCustomerDto customersDto);


    WrapperOrderedCustomers getOrderedCustomers();


    WrapperCustomerStatistic getTotalSalesByCustomer();
}
