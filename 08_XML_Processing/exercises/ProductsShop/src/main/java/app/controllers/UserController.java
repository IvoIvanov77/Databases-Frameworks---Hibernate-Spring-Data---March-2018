package app.controllers;

import app.domain.dto.p00_seed_database.WrapperUsersImportDto;
import app.domain.dto.p02_successfully_sold_products.WrapperUserWithSoldProductsDto;
import app.domain.dto.p04_users_and_products.AllUsersWithSoldProducts;
import app.services.contracts.UserService;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String importFromXmlString(String xmlString) {
        WrapperUsersImportDto usersImportDto = null;
        try {
            usersImportDto = this.xmlParser.importXml(WrapperUsersImportDto.class, xmlString);
            this.userService.create(usersImportDto);
            return "Successfully import users!";
        } catch (IOException | JAXBException e) {
            return e.getMessage();
        }
    }

    public String successfullySoldProducts(){
        WrapperUserWithSoldProductsDto userDtos = this.userService.getUsersWithSoldProducts();
        try {
            return this.xmlParser.exportXml(userDtos);
        } catch (IOException | JAXBException e) {
            return e.getMessage();
        }
    }

    public String usersAndTheirSoldProducts(){
        AllUsersWithSoldProducts allUsersWithSoldProducts = this.userService.usersWithAtLeastOneSoldProduct();
        try {
            return this.xmlParser.exportXml(allUsersWithSoldProducts);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }



}
