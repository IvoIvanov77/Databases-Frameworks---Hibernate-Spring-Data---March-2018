package app.domain.json_dto.p04_cars_with_their_list_of_parts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class PartDto {

    @Expose
    @SerializedName("Name")
    private String name;

    @Expose
    @SerializedName("Price")
    private BigDecimal price;

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
