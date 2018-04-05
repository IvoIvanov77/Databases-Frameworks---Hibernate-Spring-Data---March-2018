package app.services;

import app.dtos.GameDto;
import app.entities.Game;
import app.entities.User;
import app.repositories.GameRepository;
import app.services.contracts.GameService;
import app.utils.Mapper;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    @Override
    public String addGame(GameDto gameDto) {
        Game game = Mapper.getInstance().map(gameDto, Game.class);

        try {
            this.gameRepository.save(game);
        } catch (ConstraintViolationException cve) {
            return cve.getMessage();
        }
        return String.format("Added %s", game.getTitle());
    }

    @Override
    public String editGame(GameDto dto, Long id) {
        Game game = this.gameRepository.findOne(id);
        if (game == null) {
            return "Game doesn't exist";
        }
        game.setPrice(this.defaultWhenNull(dto.getPrice(), game.getPrice()));
        game.setTitle(this.defaultWhenNull(dto.getTitle(), game.getTitle()));
        game.setDescription(this.defaultWhenNull(dto.getDescription(), game.getDescription()));
        game.setImageThumbnailURL(this.defaultWhenNull(dto.getImageThumbnailURL(), game.getImageThumbnailURL()));
        game.setSize(this.defaultWhenNull(dto.getSize(), game.getSize()));
        game.setReleaseDate(this.defaultWhenNull(dto.getReleaseDate(), game.getReleaseDate()));
        game.setTrailer(this.defaultWhenNull(dto.getTrailer(), game.getTrailer()));
        try {
            this.gameRepository.save(game);
        } catch (ConstraintViolationException cve) {
            return cve.getMessage();
        }
        return String.format("Edited %s", game.getTitle());
    }

    @Override
    public String deleteGame(Long id) {
        Game game = this.gameRepository.findOne(id);
        if (game == null) {
            return "Game doesn't exist";
        }
        this.gameRepository.delete(id);
        return String.format("Deleted %s", game.getTitle());
    }

    @Override
    public List<GameDto> allGames() {
        return this.gameRepository.findAll()
                .stream()
                .map(game -> Mapper.getInstance().map(game, GameDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Game gameByTitle(String title) {
        return this.gameRepository.getFirstByTitle(title);

    }

    @Override
    public GameDto gameDetailsByTitle(String title) {
        Game game = this.gameRepository.getFirstByTitle(title);
        if(game == null){
            return  null;
        }
        return Mapper.getInstance().map(game, GameDto.class);
    }

    private  <T> T defaultWhenNull(T object, T def) {
        return (object == null) ? def : object;
    }

//    private <T> void setObject(T object, T value){
//        if (value != null){
//            object = value;
//        }
//    }

}
