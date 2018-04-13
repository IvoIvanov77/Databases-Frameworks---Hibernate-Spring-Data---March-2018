package app.services.impl;

import app.domain.enums.Discount;
import app.domain.models.Car;
import app.domain.models.Customer;
import app.domain.models.Part;
import app.domain.models.Sale;
import app.domain.xml_dto.p06_sales_with_applied_discount.WrapperCarWithDiscountDtos;
import app.domain.xml_dto.p06_sales_with_applied_discount.XmlSaleWithDiscountDto;
import app.domain.xml_dto.p06_sales_with_applied_discount.XmlSellCarDto;
import app.repositories.CarRepository;
import app.repositories.CustomerRepository;
import app.repositories.PartRepository;
import app.repositories.SaleRepository;
import app.services.contracts.SaleService;
import app.utils.ModelParser;
import app.utils.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public WrapperCarWithDiscountDtos getSalesWithAppliedDiscount(){
        List<Sale> allSales = this.saleRepository.findAll();
        List<XmlSaleWithDiscountDto> resultList = new ArrayList<>();
        for (Sale sale : allSales) {
            XmlSaleWithDiscountDto saleDto = setXmlSaleWithDiscountDto(sale);
            resultList.add(saleDto);
        }
        return new WrapperCarWithDiscountDtos(resultList);
    }

    private XmlSaleWithDiscountDto setXmlSaleWithDiscountDto(Sale sale) {
        Car car = sale.getCar();
        XmlSellCarDto carDto = ModelParser.getInstance().map(car, XmlSellCarDto.class);
        XmlSaleWithDiscountDto saleDto = new XmlSaleWithDiscountDto();
        saleDto.setCarDto(carDto);
        saleDto.setCustomerName(sale.getCustomer().getName());
        saleDto.setDiscount(sale.getDiscount().getValue());
        saleDto.setPrice(this.getTotalPriceOfCar(car));
        BigDecimal discount = saleDto.getPrice()
                .multiply(BigDecimal.valueOf(saleDto.getDiscount()));
        saleDto.setPriceWithDiscount(saleDto.getPrice().subtract(discount));
        return saleDto;
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
