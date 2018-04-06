package app.domain.json_dto.p01_ordered_customers;

import app.domain.models.Sale;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Set;

public class OrderedCustomerDto {

    @Expose
    @SerializedName("Id")
    private Long id;

    @Expose
    @SerializedName("Name")
    private String name;

    @Expose
    @SerializedName("BirthDate")
    private Date birthDate;

    @Expose
    @SerializedName("IsYoungDriver")
    private Boolean isYoungDriver;

    @Expose
    @SerializedName("Sales")
    private Set<Sale> sales;

    public OrderedCustomerDto() {
    }

    public OrderedCustomerDto(Long id, String name, Date birthDate, Boolean isYoungDriver, Set<Sale> sales) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
        this.sales = sales;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}


