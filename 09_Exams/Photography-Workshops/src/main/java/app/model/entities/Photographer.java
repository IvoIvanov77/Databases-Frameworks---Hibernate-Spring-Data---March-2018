package app.model.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "photographers")
public class Photographer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String firstName;

    @NotNull
    @Length(min = 2, max = 50)
    @Column
    private String lastName;

    @Pattern(regexp = "\\+\\d{1,3}/\\d{8,10}")
    @Column
    private String phone;

    @NotNull
    @ManyToOne(targetEntity = BasicCamera.class)
    private BasicCamera primaryCamera;

    @NotNull
    @ManyToOne(targetEntity = BasicCamera.class)
    private BasicCamera secondaryCamera;

    @OneToMany(mappedBy = "owner")
    private Set<Accessory> accessories;

    // TODO: 4/16/2018 change field name
    @OneToMany(mappedBy = "trainer")
    private Set<Workshop> trainerWorkshops;

    // TODO: 4/16/2018 change field name
    @ManyToMany(mappedBy = "participants")
    private Set<Workshop> participantWorkshops;

    public Photographer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BasicCamera getPrimaryCamera() {
        return primaryCamera;
    }

    public void setPrimaryCamera(BasicCamera primaryCamera) {
        this.primaryCamera = primaryCamera;
    }

    public BasicCamera getSecondaryCamera() {
        return secondaryCamera;
    }

    public void setSecondaryCamera(BasicCamera secondaryCamera) {
        this.secondaryCamera = secondaryCamera;
    }

    public Set<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(Set<Accessory> accessories) {
        this.accessories = accessories;
    }

    public Set<Workshop> getTrainerWorkshops() {
        return trainerWorkshops;
    }

    public void setTrainerWorkshops(Set<Workshop> trainerWorkshops) {
        this.trainerWorkshops = trainerWorkshops;
    }

    public Set<Workshop> getParticipantWorkshops() {
        return participantWorkshops;
    }

    public void setParticipantWorkshops(Set<Workshop> participantWorkshops) {
        this.participantWorkshops = participantWorkshops;
    }

    @Transient
    public String getFullName(){
        return String.format("%s %s", this.getFirstName(), this.getLastName());
    }
}
