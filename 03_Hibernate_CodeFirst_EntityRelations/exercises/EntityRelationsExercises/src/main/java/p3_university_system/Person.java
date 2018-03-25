package p3_university_system;

import javax.persistence.*;

//@Entity(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Basic
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
