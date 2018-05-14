package app.retake.domain.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "passports")
public class Passport implements Serializable {

    @Id
    @Pattern(regexp = "^.{7}\\d{3}$")
    private String serialNumber;

    @OneToOne(mappedBy = "passport", cascade = CascadeType.PERSIST)
    private Animal animal;

    @Length(min = 3, max = 30)
    private String ownerName;

    @Pattern(regexp = "^(\\+359|0)\\d{9}$")
    private String ownerPhoneNumber;

    private Date registeredOn;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }
}
