package app.domain.dto.json.p02_successfully_sold_products;

import app.domain.models.User;
import com.google.gson.annotations.Expose;

import java.util.List;

public class UserWithSoldProductsDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private List<SoldProductDto> soldProducts;

    public UserWithSoldProductsDto(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SoldProductDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<SoldProductDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
