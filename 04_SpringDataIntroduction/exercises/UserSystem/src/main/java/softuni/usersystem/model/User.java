package softuni.usersystem.model;

import softuni.usersystem.validation.email.Email;
import softuni.usersystem.validation.password.Password;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 4, max = 30)
    @NotNull
    @Column(name = "username", nullable = false, length = 30)
    private String username;

//    @NotNull
//    @Size(min = 6, max = 50)
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+<>?])[A-Za-z\\d!@#$%^&*()_+<>?]{6,50}")
    @Password(minLength = 4,
            maxLength = 30,
            containDigit = true,
            containLowerCaseLetter = true,
            containUpperCaseLetter = true,
            containSymbol = false
    )
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Email
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne(targetEntity = Town.class)
    private Town bornTown;

    @ManyToOne(targetEntity = Town.class)
    private Town currentlyLivingTown;

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<User> friends;

    @OneToMany(mappedBy = "user")
    private Set<Album> albums;

    @Basic
    private Date registeredOn;

    @Basic
    private Date lastTimeLoggedIn;

    @Min(1)
    @Max(120)
    private Short age;

    @OneToOne(targetEntity = Picture.class)
    private Picture profilePicture;

    @Basic
    private boolean isDeleted;

    @Basic
    private String firstName;

    @Basic
    private String lastName;

    @Transient
    private String fullName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    public Town getCurrentlyLivingTown() {
        return currentlyLivingTown;
    }

    public void setCurrentlyLivingTown(Town currentlyLivingTown) {
        this.currentlyLivingTown = currentlyLivingTown;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Date getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

    public String getFullName() {
        return String.format("%s %s", this.getFirstName(), this.getLastName());
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
