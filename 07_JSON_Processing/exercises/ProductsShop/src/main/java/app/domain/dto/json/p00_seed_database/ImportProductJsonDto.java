package app.domain.dto.json.p00_seed_database;

import app.domain.models.Product;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ImportProductJsonDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private String sellerName;

    public ImportProductJsonDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        String firstName = product.getSeller().getFirstName();
        this.sellerName = String.format("%s %s",
                firstName == null ? "" : firstName,
                product.getSeller().getLastName()).trim();
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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
