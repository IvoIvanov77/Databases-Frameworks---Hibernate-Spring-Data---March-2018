package app.services.contracts;

import app.dtos.GameDto;
import app.dtos.LoginDto;
import app.dtos.RegisterUserDto;
import app.entities.User;

import java.util.Set;

public interface UserService {
    String registerUser(RegisterUserDto userDto);

    User getUserWithGames(Long id);

    User login(LoginDto dto);

    String setBoughtItems(Set<GameDto> gameDtoSet, Long id);
}
