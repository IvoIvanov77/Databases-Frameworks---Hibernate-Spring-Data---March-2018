package softuni.usersystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.usersystem.model.User;
import softuni.usersystem.repositories.UserRepository;
import softuni.usersystem.service.contracts.UserService;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsersByEmailProvider(String provider) {
        return this.userRepository.findByEmailEndsWith(provider);
    }

    @Override
    public int removeInactiveUsers(Date date) {
        List<User> resultList = this.userRepository.findByLastTimeLoggedInBeforeAndIsDeletedIsFalse(date);
        int count = resultList.size();
        this.setDeleteOn(resultList);
        this.userRepository.saveAll(resultList);
        return count;
    }

    @Override
    public int removeInactiveUsersByQuery(Date date) {
        return this.userRepository.setUserDeleteOn(date);
    }

    private void setDeleteOn(List<User> users){
        for (User user : users) {
            user.setDeleted(true);
        }
    }
}
