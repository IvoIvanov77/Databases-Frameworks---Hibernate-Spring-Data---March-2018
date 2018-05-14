package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.dto.json_export_dto.RacingTownsExportJson;
import org.softuni.mostwanted.domain.dto.json_import_dto.RacerImportJsonDto;
import org.softuni.mostwanted.domain.dto.json_import_dto.TownImportJsonDto;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class RacerController {

    private final RacerService racerService;
    private final Parser jsonParser;

    @Autowired
    public RacerController(RacerService racerService, @Qualifier("JSONParser") Parser jsonParser) {
        this.racerService = racerService;
        this.jsonParser = jsonParser;
    }


    public String importRacersFromJson(String jsonString) {
        StringBuilder builder = new StringBuilder();

        RacerImportJsonDto[] racersDto = new RacerImportJsonDto[0];
        try {
            racersDto = this.jsonParser.read(RacerImportJsonDto[].class, jsonString);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        for (RacerImportJsonDto racerDto : racersDto) {
            if(ValidationUtil.isValid(racerDto)){
                try{
                    this.racerService.create(racerDto);
                    builder.append(String.format("Successfully imported Racer  - %s.", racerDto.getName()))
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
