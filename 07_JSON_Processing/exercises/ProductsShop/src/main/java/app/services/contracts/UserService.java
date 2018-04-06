package app.services.contracts;

import app.domain.dto.json.p00_seed_database.ImportUserJsonDto;
import app.domain.dto.json.p02_successfully_sold_products.UserWithSoldProductsDto;
import app.domain.dto.json.p04_users_and_products.AllUsersWithSoldProducts;
import app.domain.models.User;

import java.util.List;

public interface UserService {

    void create(ImportUserJsonDto dto);

    void create(List<ImportUserJsonDto> dtos);

    User findById(long id);

    User getRandomUser();


    List<UserWithSoldProductsDto> getUsersWithSoldProducts();

    AllUsersWithSoldProducts usersWithAtLeastOneSoldProduct();
}
