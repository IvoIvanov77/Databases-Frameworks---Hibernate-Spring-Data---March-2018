package app.domain.dto.p01_products_in_range;

import app.domain.models.Product;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ExportProductInRangeDto {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "price")
    private BigDecimal price;

    @XmlAttribute(name = "seller")
    private String sellerFullName;

    public ExportProductInRangeDto() {
    }

    public ExportProductInRangeDto(Product p) {
        this.name = p.getName();
        this.price = p.getPrice();
        this.sellerFullName = p.getSeller().getFullName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSellerFullName() {
        return sellerFullName;
    }

    public void setSellerFullName(String sellerFullName) {
        this.sellerFullName = sellerFullName;
    }
}
