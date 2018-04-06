package app.domain.dto.json.p04_users_and_products;

import com.google.gson.annotations.Expose;

import java.util.List;

public class SoldProductsDto {

    @Expose
    private int count;

    @Expose
    private List<ProductWithNameAndPriceDto> products;

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
