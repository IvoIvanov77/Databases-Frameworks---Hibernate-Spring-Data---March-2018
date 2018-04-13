package app.domain.dto.p04_users_and_products;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsDto {

    @XmlAttribute(name = "count")
    private int count;

    @XmlElement(name = "product")
    private List<ProductWithNameAndPriceDto> products;

    public SoldProductsDto() {
    }

    public SoldProductsDto(List<ProductWithNameAndPriceDto> products) {
        this.products = products;
        this.count = products.size();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductWithNameAndPriceDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWithNameAndPriceDto> products) {
        this.products = products;
    }
}
