package com.example.demo.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Address {

    private long id;

    private String country;

    private String city;

    private Set<Employee> citizen;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @OneToMany(mappedBy = "address")
    public Set<Employee> getCitizen() {
        return citizen;
    }

    public void setCitizen(Set<Employee> citizen) {
        this.citizen = citizen;
    }
}
