package app.services.impl;

import app.domain.enums.Discount;
import app.repositories.CarRepository;
import app.repositories.CustomerRepository;
import app.repositories.PartRepository;
import app.repositories.SaleRepository;
import app.services.contracts.SaleService;
import app.utils.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.json_dto.p04_cars_with_their_list_of_parts.CarDto;
import app.domain.json_dto.p06_sales_with_applied_discount.SaleWithDiscountDto;
import app.domain.models.Car;
import app.domain.models.Customer;
import app.domain.models.Part;
import app.domain.models.Sale;
import app.utils.ModelParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final PartRepository partRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CustomerRepository customerRepository, CarRepository carRepository, PartRepository partRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.partRepository = partRepository;
    }

    @Override
    public void createRandomSales() {
        List<Customer> customers = this.customerRepository.findAll();
        List<Car> cars = this.carRepository.findAll();
        Discount[] discounts = Discount.values();
        List<Sale> sales = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Sale sale = new Sale();
            sale.setCar(cars.remove(RandomGenerator.getInstance().nextInt(cars.size())));
            sale.setCustomer(customers.get(RandomGenerator.getInstance().nextInt(customers.size())));
            sale.setDiscount(discounts[RandomGenerator.getInstance().nextInt(discounts.length)]);
            sales.add(sale);
        }
        this.saleRepository.save(sales);
    }

    @Override
    public List<SaleWithDiscountDto> getSalesWithAppliedDiscount(){
        List<Sale> allSales = this.saleRepository.findAll();
        List<SaleWithDiscountDto> result = new ArrayList<>();
        for (Sale sale : allSales) {
            Car car = sale.getCar();
            SaleWithDiscountDto saleDto = new SaleWithDiscountDto(sale);
            saleDto.setPrice(this.getTotalPriceOfCar(car));
            CarDto carDto = ModelParser.getInstance().map(car, CarDto.class);
            saleDto.setCar(carDto);
            BigDecimal discount = saleDto.getPrice()
                    .multiply(BigDecimal.valueOf(saleDto.getDiscount()));
            saleDto.setPriceWithDiscount(saleDto.getPrice().subtract(discount));
            result.add(saleDto);
        }
        return result;
    }

    private BigDecimal getTotalPriceOfCar(Car car){
        BigDecimal totalPrice = BigDecimal.valueOf(0.0);
        Set<Part> carParts = this.partRepository.findAllByCar(car);
        for (Part carPart : carParts) {
            totalPrice = totalPrice.add(carPart.getPrice());
        }
        return totalPrice;
    }

}
