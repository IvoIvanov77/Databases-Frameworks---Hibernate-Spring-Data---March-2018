package com.masdefect.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "anomalies")
public class Anomaly implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(targetEntity = Planet.class)
    private Planet originPlanet;

    @NotNull
    @ManyToOne(targetEntity = Planet.class)
    private Planet teleportPlanet;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "anomaly_victims",
            joinColumns = { @JoinColumn(name = "anomaly_id") },
            inverseJoinColumns = { @JoinColumn(name = "person_id") })
    private Set<Person> victims;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Planet getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(Planet originPlanet) {
        this.originPlanet = originPlanet;
    }

    public Planet getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(Planet teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public Set<Person> getVictims() {
        return victims;
    }

    public void setVictims(Set<Person> victims) {
        this.victims = victims;
    }

    public void addVictim(Person person){
        this.victims.add(person);
        person.getAnomalies().add(this);
    }
}
