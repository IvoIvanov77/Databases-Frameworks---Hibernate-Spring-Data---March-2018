package org.softuni.mostwanted.domain.models;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(name = "race_entries")
public class RaceEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean hasFinished;

    private Float finishTime;

    @ManyToOne(targetEntity = Car.class)
    private Car car;

    @ManyToOne(targetEntity = Racer.class)
    private Racer racer;

    public RaceEntry() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(Boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public Float getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Float finishTime) {
        this.finishTime = finishTime;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }
}
