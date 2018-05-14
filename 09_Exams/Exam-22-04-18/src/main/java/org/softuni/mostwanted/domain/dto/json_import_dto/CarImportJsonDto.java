package org.softuni.mostwanted.domain.dto.json_import_dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CarImportJsonDto {

//    "brand" : "Volvo",
//            "model" : "C70",
//            "price" : 487452.02,
//            "yearOfProduction" : 2010,
//            "maxSpeed" : 161.6,
//            "zeroToSixty" : 2.24,
//            "racerName" : "Brigit Speller"

    @NotNull
    @Expose
    private String brand;

    @NotNull
    @Expose
    private String model;

    @Expose
    private BigDecimal price;

    @NotNull
    @Expose
    private Integer yearOfProduction;

    @Expose
    private Float maxSpeed;

    @Expose
    private Float zeroToSixty;

    @Expose
    private String racerName;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Float getZeroToSixty() {
        return zeroToSixty;
    }

    public void setZeroToSixty(Float zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    public String getRacerName() {
        return racerName;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }
}
