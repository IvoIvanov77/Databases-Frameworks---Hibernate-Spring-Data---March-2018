package app.services;

import app.dtos.GameDto;
import app.dtos.LoginDto;
import app.dtos.RegisterUserDto;
import app.entities.Game;
import app.entities.User;
import app.repositories.GameRepository;
import app.repositories.UserRepository;
import app.services.contracts.UserService;
import app.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public String registerUser(RegisterUserDto userDto){
        if(!userDto.getConfirmPassword().equals(userDto.getPassword())){
            return "The password confirmation doesn't match";
        }
        User user = Mapper.getInstance().map(userDto, User.class);
        if(this.userRepository.count() == 0){
            user.setAdmin(true);
        }
        try{
            this.userRepository.save(user);
        }catch (ConstraintViolationException cve){
            return cve.getMessage();
        }
        return String.format("%s was registered", user.getFullName());
    }

    @Override
    public User getUserWithGames(Long id){
        return  this.userRepository.getOneWithGames(id);
    }

    @Override
    public User login(LoginDto dto){
        return  this.userRepository.getLoggedInUser(dto.getEmail(), dto.getPassword());
    }

    @Override
    public String setBoughtItems(Set<GameDto> gameDtoSet, Long id){
        User user = this.userRepository.findOne(id);
        String gamesTitles = String.join(System.lineSeparator() + " -",
                gameDtoSet.stream()
                .map(GameDto::getTitle )
                .collect(Collectors.toSet()));

        Set<Game> games = gameDtoSet.stream()
                .map(gameDto -> this.gameRepository.getFirstByTitle(gameDto.getTitle()))
                .collect(Collectors.toSet());
        user.setGames(games);
        this.userRepository.save(user);
        return String.format("Successfully bought games: \n -%s" , gamesTitles);
    }
}
