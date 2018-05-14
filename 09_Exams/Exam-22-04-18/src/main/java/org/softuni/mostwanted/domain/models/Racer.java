package org.softuni.mostwanted.domain.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "racers")
public class Racer {

//    •	id – integer number, primary identification field.
//     •	name – a string (required).
//            •	age – an integer number.
//•	bounty – a decimal data type.
//            •	homeTown – a Town entity.
//•	cars – a collection of Car entity.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    private Integer age;

    private BigDecimal bounty;

    @ManyToOne(targetEntity = Town.class)
    private Town homeTown;

    @OneToMany
    private Set<Car> cars;

    public Racer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBounty() {
        return bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public Town getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
