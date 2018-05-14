package org.softuni.mostwanted.domain.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car {

//    •	id – integer number, primary identification field.
//            •	brand – a string (required).
//            •	model – a string (required).
//            •	price – a decimal data type.
//            •	yearOfProduction – an integer number (required).
//            •	maxSpeed – a floating-point data type.
//•	zeroToSixty – a floating-point data type.
//•	racer – a Racer entity.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    private BigDecimal price;

    @Column(name = "year_of_production", nullable = false)
    private Integer yearOfProduction;

    private Float maxSpeed;

    private Float zeroToSixty;

    @ManyToOne(targetEntity = Racer.class)
    private Racer racer;

    public Car() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }
}
