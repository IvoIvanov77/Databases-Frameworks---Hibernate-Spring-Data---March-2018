package app.domain.dto.p04_users_and_products;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class AllUsersWithSoldProducts {

    @XmlAttribute(name = "count")
    private int usersCount;

    @XmlElement(name = "user")
    private List<UserWithAtLeastOneSoldProductDto> users;

    public AllUsersWithSoldProducts() {
    }

    public AllUsersWithSoldProducts(List<UserWithAtLeastOneSoldProductDto> users) {
        this.usersCount = users.size();
        this.users = users;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserWithAtLeastOneSoldProductDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithAtLeastOneSoldProductDto> users) {
        this.users = users;
    }
}
