package app.domain.dto.p04_users_and_products;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithAtLeastOneSoldProductDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute(name = "age")
    private int age;

    @XmlElement(name = "sold-products")
    private SoldProductsDto soldProducts;

//    public UserWithAtLeastOneSoldProductDto(User user) {
//        this.firstName = user.getFirstName();
//        this.lastName = user.getLastName();
//        this.age = user.getAge();
//    }

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

    public SoldProductsDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldProductsDto soldProducts) {
        this.soldProducts = soldProducts;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
