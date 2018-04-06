package app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.json_dto.p00_import_data.ImportCustomerFromJsonDto;
import app.domain.json_dto.p01_ordered_customers.OrderedCustomerDto;
import app.domain.json_dto.p05_total_sales_by_customer.CustomerStatisticDto;
import app.domain.models.Car;
import app.domain.models.Customer;
import app.domain.models.Part;
import app.domain.models.Sale;
import app.repositories.CustomerRepository;
import app.repositories.PartRepository;
import app.repositories.SaleRepository;
import app.services.contracts.CustomerService;
import app.utils.ModelParser;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final PartRepository partRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, SaleRepository saleRepository, PartRepository partRepository) {
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.partRepository = partRepository;
    }

    @Override
    public void create(ImportCustomerFromJsonDto[] customersDto) {
        List<Customer> customersToImport = Arrays.stream(customersDto)
                .map(dto -> ModelParser.getInstance().map(dto, Customer.class))
                .collect(Collectors.toList());
        this.customerRepository.save(customersToImport);
    }


    @Override
    public List<OrderedCustomerDto> getOrderedCustomers(){
        return this.customerRepository.orderedCustomers()
                .stream()
                .map(this::mapObjectArrayToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerStatisticDto> getTotalSalesByCustomer(){
        return this.customerRepository.totalSalesByCustomer();
    }




    private OrderedCustomerDto mapObjectArrayToDto(Object[] o){
        Long id = (Long)o[0];
        String name = o[1].toString();
        Date birthDate = (Date)o[2];
        Boolean isYoungDriver = Boolean.valueOf(o[3].toString());
        Set<Sale>sales = new HashSet<>();
        return new OrderedCustomerDto(id, name, birthDate, isYoungDriver, sales);
    }


}
