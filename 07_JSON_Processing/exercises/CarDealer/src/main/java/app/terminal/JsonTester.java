package app.terminal;

import app.domain.json_dto.p05_total_sales_by_customer.CustomerStatisticDto;
import app.repositories.CarRepository;
import app.repositories.CustomerRepository;
import app.repositories.PartRepository;
import app.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class JsonTester implements CommandLineRunner {

    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public JsonTester(PartRepository partRepository, CarRepository carRepository, CustomerRepository customerRepository, SaleRepository saleRepository) {
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
    }


    @Override
    public void run(String... strings) throws Exception {
        List<CustomerStatisticDto> list = this.customerRepository.totalSalesByCustomer();

        System.out.println(list);
    }
}
