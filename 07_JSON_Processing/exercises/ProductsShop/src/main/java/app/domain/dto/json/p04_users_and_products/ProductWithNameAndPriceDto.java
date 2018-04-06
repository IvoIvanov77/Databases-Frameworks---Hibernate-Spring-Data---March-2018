package app.domain.dto.json.p04_users_and_products;

import app.domain.models.Product;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductWithNameAndPriceDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    public ProductWithNameAndPriceDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();

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


}
