package app.domain.xml_dto.p06_sales_with_applied_discount;

import app.domain.models.Sale;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "sale")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class XmlSaleWithDiscountDto {

    @XmlElement(name = "car")
    private XmlSellCarDto carDto;

    @XmlElement(name = "customer-name")
    private String customerName;

    @XmlElement(name = "discount")
    private float discount;

    @XmlElement(name = "price")
    private BigDecimal price;

    @XmlElement(name = "price-with-discount")
    private BigDecimal priceWithDiscount;

    public XmlSaleWithDiscountDto() {
    }

    public XmlSaleWithDiscountDto(Sale sale) {
        this.customerName = sale.getCustomer().getName();
        this.discount = sale.getDiscount().getValue();
    }

    public XmlSellCarDto getCarDto() {
        return carDto;
    }

    public void setCarDto(XmlSellCarDto carDto) {
        this.carDto = carDto;
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
