package app.controllers;


import app.dtos.GameDto;
import app.entities.Game;
import app.entities.User;
import app.services.contracts.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class GameController extends BaseController {

    private final GameService gameService;
    private final DateFormat format;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
        this.format = new SimpleDateFormat("dd-MM-yyyy");
    }

    public String addNewGame(String input){
        if(!userSession.isCurrentUserAdmin()){
            return "You don't have permission to add game";
        }
        String[] params = input.split("\\|");
        String title = params[1];
        BigDecimal price = new BigDecimal(params[2]);
        BigDecimal size = new BigDecimal(params[3]);
        String trailer = params[4];
        String thumbnailURL = params[5];
        String description = params[6];
        Date releaseDate = null;
        try {
            releaseDate = this.format.parse(params[7]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        GameDto gameDto = new GameDto(title, price, size, trailer, thumbnailURL,
                description, releaseDate);

        return this.gameService.addGame(gameDto);
    }

    public String editGame(String input){
        if(!userSession.isCurrentUserAdmin()){
            return "You don't have permission to edit game";
        }
        String[] params = input.split("\\|");

        Long id = Long.parseLong(params[1]);
        Map<String, String> fieldValueMap = new HashMap<>();
        for (int i = 2; i < params.length; i++) {
            String[] keyValue = params[i].split("\\s*=\\s*");
            fieldValueMap.put(keyValue[0], keyValue[1]);
        }
        GameDto dto = new GameDto(fieldValueMap);

        return this.gameService.editGame(dto, id);
    }

    public String deleteGame(String input){
        if(!userSession.isCurrentUserAdmin()){
            return "You don't have permission to delete game";
        }
        String[] params = input.split("\\|");
        Long id = Long.parseLong(params[1]);
        return this.gameService.deleteGame(id);
    }

    public String getAllGames(){
        List<String> gamesToString = this.gameService.allGames()
                .stream()
                .map(gameDto -> String.format("%s %s", gameDto.getTitle(), gameDto.getPrice()))
                .collect(Collectors.toList());
        return String.join(System.lineSeparator(), gamesToString);
    }

    public String showDetails(String input){
        String[] params = input.split("\\|");
        String title = params[1];
        GameDto gameDto = this.gameService.gameDetailsByTitle(title);
        if(gameDto == null){
            return "Game doesn't exist";
        }
        return String.format(
                "Title: %s\n" +
                "Price: %s\n" +
                "Description: %s\n" +
                "Release date: %s\n",
                gameDto.getTitle(),
                gameDto.getPrice(),
                gameDto.getDescription(),
                this.format.format(gameDto.getReleaseDate())
        );
    }

    public String addItemToShoppingCart(String input){
        if(!userSession.isCurrentUserLoggedIn()){
            return "Please log in";
        }
        String[] params = input.split("\\|");
        String title = params[1];
        Game game = this.gameService.gameByTitle(title);
        if(game == null){
            return "Game doesn't exist";
        }
        if(userSession.getCurrentUser().getGames().contains(game)){
            return String.format("%s already exists in your games.", game.getTitle());
        }
        if(userSession.addItemToShoppingCart(game)){
            return String.format("%s added to cart.", game.getTitle());
        }else {
            return String.format("%s already exists in cart.", game.getTitle());
        }

    }

    public String removeItemFromShoppingCart(String input){
        if(!userSession.isCurrentUserLoggedIn()){
            return "Please log in";
        }
        User user = userSession.getCurrentUser();
        String[] params = input.split("\\|");
        String title = params[1];
        Game game = this.gameService.gameByTitle(title);
        if(game == null){
            return "Game doesn't exist";
        }
        if(userSession.deleteItemFromShoppingCart(game)){
            return String.format("%s removed from cart.", game.getTitle());
        }else {
            return String.format("%s doesn't exists in cart.", game.getTitle());
        }

    }


}
