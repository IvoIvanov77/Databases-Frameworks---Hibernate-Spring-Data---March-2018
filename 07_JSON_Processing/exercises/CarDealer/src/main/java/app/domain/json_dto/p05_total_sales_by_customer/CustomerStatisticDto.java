package app.domain.json_dto.p05_total_sales_by_customer;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CustomerStatisticDto {

    @Expose
    private String fullName;

    @Expose
    private Long boughtCars;

    @Expose
    private BigDecimal spentMoney;

    public CustomerStatisticDto() {
    }

    public CustomerStatisticDto(String fullName, Long boughtCars, BigDecimal spentMoney) {
        this.fullName = fullName;
        this.boughtCars = boughtCars;
        this.spentMoney = spentMoney;
    }

    public String getFullName() {
        return fullName;
    }

    public Long getBoughtCars() {
        return boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public CustomerStatisticDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public CustomerStatisticDto setBoughtCars(Long boughtCars) {
        this.boughtCars = boughtCars;
        return this;
    }

    public CustomerStatisticDto setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
        return this;
    }
}
