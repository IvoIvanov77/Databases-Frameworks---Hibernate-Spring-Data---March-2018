package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.dto.json_import_dto.DistrictImportJsonDto;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.DistrictService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class DistrictController {

    private final DistrictService districtService;
    private final Parser jsonParser;

    public DistrictController(DistrictService districtService,
                              @Qualifier("JSONParser") Parser jsonParser) {
        this.districtService = districtService;
        this.jsonParser = jsonParser;
    }


    public String importTownsFromJson(String jsonString) {

        StringBuilder builder = new StringBuilder();

       DistrictImportJsonDto[] districtsDtos = new DistrictImportJsonDto[0];
        try {
            districtsDtos = this.jsonParser.read(DistrictImportJsonDto[].class, jsonString);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        for ( DistrictImportJsonDto districtDto : districtsDtos) {
            if(ValidationUtil.isValid(districtDto)){
                try{
                    this.districtService.create(districtDto);
                    builder.append(String.format("Successfully imported District - %s.", districtDto.getName()))
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
