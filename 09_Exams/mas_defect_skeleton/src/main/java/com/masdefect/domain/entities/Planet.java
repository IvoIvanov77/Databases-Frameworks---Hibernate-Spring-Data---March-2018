package com.masdefect.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "planets")
public class Planet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @ManyToOne(targetEntity = SolarSystem.class)
    private SolarSystem solarSystem;

    @NotNull
    @ManyToOne(targetEntity = Star.class)
    private Star sun;

    @OneToMany(mappedBy = "homePlanet")
    private Set<Person> people;

    @OneToMany(mappedBy = "teleportPlanet")
    private Set<Anomaly> teleportPlanetAnomalies;

    @OneToMany(mappedBy = "originPlanet")
    private Set<Anomaly> originPlanetAnomalies;

    public Set<Anomaly> getOriginPlanetAnomalies() {
        return originPlanetAnomalies;
    }

    public void setOriginPlanetAnomalies(Set<Anomaly> originPlanetAnomalies) {
        this.originPlanetAnomalies = originPlanetAnomalies;
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

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    public Star getSun() {
        return sun;
    }

    public void setSun(Star sun) {
        this.sun = sun;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Anomaly> getTeleportPlanetAnomalies() {
        return teleportPlanetAnomalies;
    }

    public void setTeleportPlanetAnomalies(Set<Anomaly> teleportPlanetAnomalies) {
        this.teleportPlanetAnomalies = teleportPlanetAnomalies;
    }
}
