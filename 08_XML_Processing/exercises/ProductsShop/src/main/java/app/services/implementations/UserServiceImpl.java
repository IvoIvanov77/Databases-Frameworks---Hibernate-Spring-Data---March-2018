package app.services.implementations;

import app.domain.dto.p00_seed_database.WrapperUsersImportDto;
import app.domain.dto.p02_successfully_sold_products.SoldProductDto;
import app.domain.dto.p02_successfully_sold_products.UserWithSoldProductsDto;
import app.domain.dto.p02_successfully_sold_products.WrapperUserWithSoldProductsDto;
import app.domain.dto.p04_users_and_products.AllUsersWithSoldProducts;
import app.domain.dto.p04_users_and_products.ProductWithNameAndPriceDto;
import app.domain.dto.p04_users_and_products.SoldProductsDto;
import app.domain.dto.p04_users_and_products.UserWithAtLeastOneSoldProductDto;
import app.domain.models.User;
import app.repositories.UserRepository;
import app.services.contracts.UserService;
import app.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void create(WrapperUsersImportDto dtos) {
        List<User> usersToImport = dtos.getUsers()
                .stream()
                .map(dto -> ModelParser.getInstance().map(dto, User.class))
                .collect(Collectors.toList());
        this.userRepository.save(usersToImport);
    }

    @Override
    public WrapperUserWithSoldProductsDto getUsersWithSoldProducts(){
        List<UserWithSoldProductsDto> resultList = this.userRepository.findBySoldProducts()
                .stream()
                .map(user -> {
                    UserWithSoldProductsDto userDto = ModelParser.getInstance()
                            .map(user, UserWithSoldProductsDto.class);
                    userDto.setSoldProducts(user.getSoldProducts()
                        .stream()
                        .map(product -> ModelParser.getInstance().map(product, SoldProductDto.class))
                        .collect(Collectors.toList()));
                    return userDto;
                })
                .collect(Collectors.toList());
        WrapperUserWithSoldProductsDto userDtos = new WrapperUserWithSoldProductsDto();
        userDtos.setUsers(resultList);
        return userDtos;
    }

    @Override
    public AllUsersWithSoldProducts usersWithAtLeastOneSoldProduct(){

        List<UserWithAtLeastOneSoldProductDto> allUsersDto =
                this.userRepository.findAllUsersWithSoldProducts()
                .stream()
                .sorted((u1, u2) -> Integer.compare(u2.getSoldProducts().size(), u1.getSoldProducts().size()))
                .map(this::mapToUserWithAtLeastOneSoldProductDto)
                .collect(Collectors.toList());

        return new AllUsersWithSoldProducts(allUsersDto);
    }


    private UserWithAtLeastOneSoldProductDto mapToUserWithAtLeastOneSoldProductDto(User user) {
        UserWithAtLeastOneSoldProductDto userDto = ModelParser.getInstance()
                .map(user, UserWithAtLeastOneSoldProductDto.class);
        List<ProductWithNameAndPriceDto> productsDtos = user.getSoldProducts()
                .stream()
                .map(product -> ModelParser.getInstance().map(product, ProductWithNameAndPriceDto.class))
                .collect(Collectors.toList());
        SoldProductsDto soldProductsDto = new SoldProductsDto(productsDtos);
        userDto.setSoldProducts(soldProductsDto);
        return userDto;
    }
}
