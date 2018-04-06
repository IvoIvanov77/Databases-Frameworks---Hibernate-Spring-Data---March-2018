package app.domain.json_dto.p06_sales_with_applied_discount;

import app.domain.json_dto.p04_cars_with_their_list_of_parts.CarDto;
import com.google.gson.annotations.Expose;
import app.domain.models.Sale;

import java.math.BigDecimal;

public class SaleWithDiscountDto {

    @Expose
    private CarDto car;

    @Expose
    private String customerName;

    @Expose
    private float discount;

    @Expose
    private BigDecimal price;

    @Expose
    private BigDecimal priceWithDiscount;

    public SaleWithDiscountDto(Sale sale) {
        this.customerName = sale.getCustomer().getName();
        this.discount = sale.getDiscount().getValue();
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
