package app.terminal;

import app.controllers.GameController;
import app.controllers.OrderController;
import app.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
public class CommandInterpreter {

    private final UserController userController;
    private final OrderController orderController;
    private final GameController gameController;
    private BufferedReader reader;

    @Autowired
    public CommandInterpreter(UserController userController,
                         OrderController orderController,
                         GameController gameController) {
        this.userController = userController;
        this.orderController = orderController;
        this.gameController = gameController;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String executeCommands() throws IOException {
        String line;
        StringBuilder builder = new StringBuilder();
        while (true){
            if("quit".equalsIgnoreCase(line = reader.readLine())){
                break;
            }
            builder.append(this.executeCommand(line))
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    private String executeCommand(String line){
        String commandName = line.split("\\|")[0];

        switch (commandName){
            case "RegisterUser" : return this.userController.registerNewUser(line);
            case "LoginUser" : return this.userController.login(line);
            case "LogoutUser" : return this.userController.logOut();
            case "AddGame" : return this.gameController.addNewGame(line);
            case "EditGame" : return this.gameController.editGame(line);
            case "DeleteGame" : return this.gameController.deleteGame(line);
            case "AllGame" : return this.gameController.getAllGames();
            case "DetailGame" : return this.gameController.showDetails(line);
            case "OwnedGame" : return this.userController.getOwnedGames();
            case "AddItem" : return this.gameController.addItemToShoppingCart(line);
            case "RemoveItem" : return this.gameController.removeItemFromShoppingCart(line);
            case "BuyItem" : return this.orderController.buyItem();
            default:return "Invalid command";
        }
    }
}
