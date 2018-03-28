package softuni.usersystem.terminal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.usersystem.controller.UserController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserController userController;

    private DateFormat dateFormat;

    @Autowired
    public ConsoleRunner(UserController userController) {
        this.userController = userController;
        this.dateFormat = new SimpleDateFormat("dd MMM yyyy");
    }


    @Override
    public void run(String... strings) throws Exception {
//        System.out.println(dateFormat.format(new Date()));
        System.out.println(getUsersByEmailProvider("abv.bg"));
        System.out.println(removeInactiveUsers("01 Oct 2014"));
    }

    private String getUsersByEmailProvider(String provider){
        return this.userController.getUsersByEmailProvider(provider);
    }

    private String removeInactiveUsers(String date){
        return this.userController.removeInactiveUsers(date, this.dateFormat, false);
    }

    private String removeInactiveUsersByQuery(String date){
        return this.userController.removeInactiveUsers(date, this.dateFormat, true);
    }


}
