package org.softuni.mostwanted.domain.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "races")
public class Race {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "laps", nullable = false)
    private int laps;

    @ManyToOne(targetEntity = District.class)
    private District district;

    @OneToMany(targetEntity = RaceEntry.class)
    private Set<RaceEntry> entries;

    public Race() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Set<RaceEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<RaceEntry> entries) {
        this.entries = entries;
    }

}
