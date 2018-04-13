package app.domain.dto.p02_successfully_sold_products;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperUserWithSoldProductsDto {

    @XmlElement(name = "user")
    private List<UserWithSoldProductsDto> users;

    public List<UserWithSoldProductsDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithSoldProductsDto> users) {
        this.users = users;
    }
}
