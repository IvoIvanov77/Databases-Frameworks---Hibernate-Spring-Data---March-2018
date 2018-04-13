package app.services.contracts;

import app.domain.dto.p00_seed_database.WrapperUsersImportDto;
import app.domain.dto.p02_successfully_sold_products.WrapperUserWithSoldProductsDto;
import app.domain.dto.p04_users_and_products.AllUsersWithSoldProducts;

public interface UserService {

    void create(WrapperUsersImportDto dtos);

    WrapperUserWithSoldProductsDto getUsersWithSoldProducts();

    AllUsersWithSoldProducts usersWithAtLeastOneSoldProduct();
}
