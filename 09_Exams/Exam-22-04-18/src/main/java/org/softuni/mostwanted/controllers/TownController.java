package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.dto.json_import_dto.TownImportJsonDto;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class TownController {

    private final TownService townService;
    private final Parser jsonParser;

    @Autowired
    public TownController(TownService townService, @Qualifier("JSONParser") Parser jsonParser) {
        this.townService = townService;
        this.jsonParser = jsonParser;
    }

    public String importTownsFromJson(String jsonString) {
        StringBuilder builder = new StringBuilder();

        TownImportJsonDto[] townDtos = new TownImportJsonDto[0];
        try {
            townDtos = this.jsonParser.read(TownImportJsonDto[].class, jsonString);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        for (TownImportJsonDto townDto : townDtos) {
            if(ValidationUtil.isValid(townDto)){
                try{
                    this.townService.create(townDto);
                    builder.append(String.format("Successfully imported Town - %s.", townDto.getName()))
                            .append(System.lineSeparator());
                }catch (IllegalArgumentException iae){
                    builder.append(iae.getMessage()).append(System.lineSeparator());
                }

            }else{
                builder.append("Error: Incorrect Data!").append(System.lineSeparator());
            }
        }

        return builder.toString().trim();
    }
}
