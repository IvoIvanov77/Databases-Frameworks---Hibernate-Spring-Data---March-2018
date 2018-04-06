package app.domain.dto.json.p01_products_in_range;

import app.domain.models.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class ExportProductInRangeDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    @SerializedName("seller")
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
