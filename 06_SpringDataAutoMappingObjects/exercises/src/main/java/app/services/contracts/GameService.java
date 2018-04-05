package app.services.contracts;

import app.dtos.GameDto;
import app.entities.Game;

import java.util.List;

public interface GameService {


    String addGame(GameDto gameDto);

    String editGame(GameDto dto, Long id);

    String deleteGame(Long id);

    List<GameDto> allGames();

    Game gameByTitle(String title);

    GameDto gameDetailsByTitle(String title);
}
