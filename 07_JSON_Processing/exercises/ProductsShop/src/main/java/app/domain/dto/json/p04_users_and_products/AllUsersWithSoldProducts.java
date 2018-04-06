package app.domain.dto.json.p04_users_and_products;

import com.google.gson.annotations.Expose;

import java.util.List;

public class AllUsersWithSoldProducts {

    @Expose
    private int usersCount;

    @Expose
    private List<UserWithAtLeastOneSoldProductDto> users;

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
