package app.controllers;

import app.dtos.LoginDto;
import app.dtos.RegisterUserDto;
import app.entities.Game;
import app.entities.User;
import app.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController extends BaseController{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public String registerNewUser(String input){
//        •	RegisterUser|<email>|<password>|<confirmPassword>|<fullName>
        String[] params = input.split("\\|");
        String email =params[1];
        String password = params[2];
        String confirmPassword = params[3];
        String fullName = params[4];
        RegisterUserDto userDto = new RegisterUserDto(email, password, confirmPassword, fullName);
        return this.userService.registerUser(userDto);
    }

    public String login(String input){
        String[] params = input.split("\\|");
        String email =params[1];
        String password = params[2];
        LoginDto dto = new LoginDto(email, password);
        User user = this.userService.login(dto);
        if(user == null){
            return "Incorrect username / password";
        }else{
            userSession.login(user);
            return String.format("Successfully logged as %s", user.getFullName());
        }
    }

    public String logOut(){
        if(userSession.isCurrentUserLoggedIn()){
            String fullName = userSession.getCurrentUser().getFullName();
            userSession.logout();
            return String.format("User %s successfully logged out", fullName);
        }
        return "Cannot log out. No user was logged in.";
    }

    public String getOwnedGames(){
        if(!userSession.isCurrentUserLoggedIn()){
            return "Please log in";
        }
        User user = userSession.getCurrentUser();
        List<String> gamesNames = this.userService.getUserWithGames(user.getId()).getGames()
                .stream()
                .map(Game::getTitle)
                .collect(Collectors.toList());

        return String.join(System.lineSeparator(), gamesNames);
    }



}
