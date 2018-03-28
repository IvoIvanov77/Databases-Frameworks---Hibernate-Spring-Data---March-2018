package softuni.usersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import softuni.usersystem.model.User;
import softuni.usersystem.service.contracts.UserService;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public String getUsersByEmailProvider(String provider){
        List<String> resultList = this.userService.getUsersByEmailProvider(provider)
                .stream()
                .map(user -> String.format("%s %s", user.getUsername(), user.getEmail()))
                .collect(Collectors.toList());

        return String.join(System.lineSeparator(), resultList);
    }

    public String removeInactiveUsers(String input, DateFormat format, Boolean useQuery){
        Date date;
        try {
            date = format.parse(input);
        } catch (ParseException e) {
            return e.getMessage();
        }
        int count = useQuery ? this.userService.removeInactiveUsersByQuery(date) :
                this.userService.removeInactiveUsers(date);

        switch (count){
            case 0 : return "No users have been deleted";
            case 1 : return "1 user has been deleted";
            default: return String.format("%d users have been deleted", count);
        }

    }



}
