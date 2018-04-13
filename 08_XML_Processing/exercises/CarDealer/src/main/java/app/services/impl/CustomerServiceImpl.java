package app.services.impl;

import app.domain.models.Customer;
import app.domain.xml_dto.p00_import_data.WrapperImportCustomerDto;
import app.domain.xml_dto.p01_ordered_customers.WrapperOrderedCustomers;
import app.domain.xml_dto.p01_ordered_customers.XmlOrderedCustomerDto;
import app.domain.xml_dto.p05_total_sales_by_customer.WrapperCustomerStatistic;
import app.repositories.CustomerRepository;
import app.services.contracts.CustomerService;
import app.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void create(WrapperImportCustomerDto customersDto) {
        List<Customer> customersToImport = customersDto.getCustomerFromXmlDtos()
                .stream()
                .map(dto -> ModelParser.getInstance().map(dto, Customer.class))
                .collect(Collectors.toList());
        this.customerRepository.save(customersToImport);
    }


    @Override
    public WrapperOrderedCustomers getOrderedCustomers(){
        List<XmlOrderedCustomerDto> orderedCustomerDto = this.customerRepository.orderedCustomers();
        WrapperOrderedCustomers orderedCustomers = new WrapperOrderedCustomers();
        orderedCustomers.setOrderedCustomerDtos(orderedCustomerDto);
        return orderedCustomers;
    }

    @Override
    public WrapperCustomerStatistic getTotalSalesByCustomer(){
        return new WrapperCustomerStatistic(this.customerRepository.totalSalesByCustomer());
    }

}
