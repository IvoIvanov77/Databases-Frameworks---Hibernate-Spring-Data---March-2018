package softuni.usersystem.service.contracts;

import softuni.usersystem.model.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    List<User> getUsersByEmailProvider(String provider);

    int removeInactiveUsers(Date date);

    int removeInactiveUsersByQuery(Date date);
}
