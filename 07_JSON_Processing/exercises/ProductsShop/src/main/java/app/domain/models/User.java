package app.domain.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

//    •	User have an id, first name (optional) and last name (at least 3 characters) and age (optional).

    private long id;

    private String firstName;

    private String lastName;

    private String fullName;

    private int age;

//    •	Users should have many products sold and many products bought.
    private List<Product> soldProducts;

    private List<Product> boughtProducts;

    private Set<User> friends;

    public User() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Length(min = 3)
    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    public List<Product> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<Product> soldProducts) {
        this.soldProducts = soldProducts;
    }

    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
    public List<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "friend_id") })
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @Transient
    public String getFullName() {
        return String.format("%s, %s", this.getFirstName(), this.getLastName());
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

