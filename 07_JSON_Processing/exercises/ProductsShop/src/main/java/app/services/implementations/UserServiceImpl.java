package app.services.implementations;

import app.domain.dto.json.p00_seed_database.ImportUserJsonDto;
import app.domain.dto.json.p02_successfully_sold_products.SoldProductDto;
import app.domain.dto.json.p02_successfully_sold_products.UserWithSoldProductsDto;
import app.domain.dto.json.p04_users_and_products.AllUsersWithSoldProducts;
import app.domain.dto.json.p04_users_and_products.ProductWithNameAndPriceDto;
import app.domain.dto.json.p04_users_and_products.SoldProductsDto;
import app.domain.dto.json.p04_users_and_products.UserWithAtLeastOneSoldProductDto;
import app.domain.models.User;
import app.repositories.UserRepository;
import app.services.contracts.UserService;
import app.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(ImportUserJsonDto dto) {
        User user = Mapper.getInstance().map(dto, User.class);
        this.userRepository.save(user);
    }

    @Override
    public void create(List<ImportUserJsonDto> dtos) {
        Iterable<User> users = dtos.stream()
                .map(dto -> Mapper.getInstance().map(dto, User.class))
                .collect(Collectors.toList());
        this.userRepository.save(users);
    }

    @Override
    public User findById(long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User getRandomUser() {
        Random random = new Random();
        long userId = random.nextInt((int)this.userRepository.count()) + 1;

        return this.userRepository.findOne(userId);
    }

    @Override
    public List<UserWithSoldProductsDto> getUsersWithSoldProducts(){
        List<UserWithSoldProductsDto> resultList = this.userRepository.findBySoldProducts()
                .stream()
                .map(user -> {
                    UserWithSoldProductsDto userDto = new UserWithSoldProductsDto(user);
                    userDto.setSoldProducts(user.getSoldProducts()
                        .stream()
                        .map(SoldProductDto::new)
                        .collect(Collectors.toList()));
                    return userDto;
                })
                .collect(Collectors.toList());
        return resultList;
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
        UserWithAtLeastOneSoldProductDto userDto = new UserWithAtLeastOneSoldProductDto(user);
        List<ProductWithNameAndPriceDto> productsDtos = user.getSoldProducts()
                .stream()
                .map(ProductWithNameAndPriceDto::new)
                .collect(Collectors.toList());
        SoldProductsDto soldProductsDto = new SoldProductsDto(productsDtos);
        userDto.setSoldProducts(soldProductsDto);
        return userDto;
    }
}
