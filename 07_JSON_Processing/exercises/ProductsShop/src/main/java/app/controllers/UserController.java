package app.controllers;

import app.domain.dto.json.p00_seed_database.ImportUserJsonDto;
import app.domain.dto.json.p02_successfully_sold_products.UserWithSoldProductsDto;
import app.domain.dto.json.p04_users_and_products.AllUsersWithSoldProducts;
import app.io.JSONParser;
import app.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final JSONParser jsonParser;

    @Autowired
    public UserController(UserService userService, JSONParser jsonParser) {
        this.userService = userService;
        this.jsonParser = jsonParser;
    }


    public void importUsersFromJson(){
        ImportUserJsonDto[] usersDto = this.jsonParser
                .importJson(ImportUserJsonDto[].class, "users.json");

        this.userService.create(Arrays.asList(usersDto));
    }

    public String successfullySoldProducts(){
        List<UserWithSoldProductsDto> userDtos = this.userService.getUsersWithSoldProducts();
        return this.jsonParser.exportJson(userDtos);
    }

    public String usersAndTheirSoldProducts(){
        AllUsersWithSoldProducts allUsersWithSoldProducts = this.userService.usersWithAtLeastOneSoldProduct();
        return this.jsonParser.exportJson(allUsersWithSoldProducts);
    }



}
