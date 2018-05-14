package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.dto.json_import_dto.CarImportJsonDto;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class CarController {

    private final CarService carService;
    private final Parser jsonParser;

    @Autowired
    public CarController(CarService carService, @Qualifier("JSONParser") Parser jsonParser) {
        this.carService = carService;
        this.jsonParser = jsonParser;
    }

    public String importCarsFromJson(String jsonString) {
        StringBuilder builder = new StringBuilder();

        CarImportJsonDto[] carsDto = new CarImportJsonDto[0];
        try {
            carsDto = this.jsonParser.read(CarImportJsonDto[].class, jsonString);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        for (CarImportJsonDto dto : carsDto) {
            if(ValidationUtil.isValid(dto)){
                try{
                    this.carService.create(dto);
                    builder.append(String.format("Successfully imported Car - %s %s @ %d",
                            dto.getBrand(), dto.getModel(), dto.getYearOfProduction()))
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
