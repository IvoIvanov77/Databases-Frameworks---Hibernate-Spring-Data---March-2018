package app.domain.xml_dto.p05_total_sales_by_customer;

import app.domain.models.Car;
import app.domain.models.Customer;
import app.domain.models.Part;
import app.domain.models.Sale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Set;

@XmlRootElement(name = "customer")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class XMLCustomerStatisticDto {

    @XmlAttribute(name = "full_name")
    private String fullName;

    @XmlAttribute(name = "bought-cars")
    private Long boughtCars;

    @XmlAttribute(name = "spent-money")
    private BigDecimal spentMoney;

    public XMLCustomerStatisticDto() {
    }

    public XMLCustomerStatisticDto(String fullName, Long boughtCars, BigDecimal spentMoney) {
        this.fullName = fullName;
        this.boughtCars = boughtCars;
        this.spentMoney = spentMoney;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Long boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }

    private BigDecimal calculateSpentMoney(Set<Sale> sales) {
        BigDecimal result = BigDecimal.valueOf(0.0);

        for (Sale sale : sales) {
            BigDecimal carPrice = getPriceWithDiscount(sale);
            result = result.add(carPrice);
        }

        return result;
    }

    private BigDecimal getPriceWithDiscount(Sale sale) {
        Car car = sale.getCar();
        BigDecimal carPrice = BigDecimal.valueOf(0.0);
        for (Part part : car.getParts()) {
            carPrice = carPrice.add(part.getPrice());
        }
        carPrice = carPrice.subtract(
                carPrice.multiply(BigDecimal.valueOf(sale.getDiscount().getValue()))
        );
        return carPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
