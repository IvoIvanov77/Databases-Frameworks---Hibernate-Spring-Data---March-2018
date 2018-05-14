package app.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "cameras")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "camera_type")
public abstract class BasicCamera  {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    private String make;

    @NotNull
    private String model;

    private boolean isFullFrame;

    @NotNull
    @Min(100)
    private Integer minISO;

    @Column
    private Integer maxIso;

    @OneToMany(mappedBy = "primaryCamera")
    private Set<Photographer> primaryCameraPhotographers;

    @OneToMany(mappedBy = "secondaryCamera")
    private Set<Photographer> secondaryCameraPhotographers;

    public BasicCamera() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isFullFrame() {
        return isFullFrame;
    }

    public void setFullFrame(boolean fullFrame) {
        isFullFrame = fullFrame;
    }

    public Integer getMinISO() {
        return minISO;
    }

    public void setMinISO(Integer minISO) {
        this.minISO = minISO;
    }

    public Integer getMaxIso() {
        return maxIso;
    }

    public void setMaxIso(Integer maxIso) {
        this.maxIso = maxIso;
    }

    public Set<Photographer> getPrimaryCameraPhotographers() {
        return primaryCameraPhotographers;
    }

    public void setPrimaryCameraPhotographers(Set<Photographer> primaryCameraPhotographers) {
        this.primaryCameraPhotographers = primaryCameraPhotographers;
    }

    public Set<Photographer> getSecondaryCameraPhotographers() {
        return secondaryCameraPhotographers;
    }

    public void setSecondaryCameraPhotographers(Set<Photographer> secondaryCameraPhotographers) {
        this.secondaryCameraPhotographers = secondaryCameraPhotographers;
    }
}
