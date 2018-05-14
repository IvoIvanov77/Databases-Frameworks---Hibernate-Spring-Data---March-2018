package com.masdefect.controller;

import com.masdefect.domain.dto.json.PersonImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class PersonsController {

    @Autowired
    private PersonService planetService;
    @Autowired
    @Qualifier("JSONParser")
    private FileParser fileParser;


    public String importDataFromJSON(String fileContent){
        PersonImportJSONDto[] dtos = new PersonImportJSONDto[0];
        try {
            dtos = this.fileParser.read(PersonImportJSONDto[].class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();

        for (PersonImportJSONDto dto : dtos) {
            try{
                this.planetService.create(dto);
                builder.append(String.format("Successfully imported Person %s.",
                        dto.getName()))
                        .append(System.lineSeparator());
            }catch (IllegalArgumentException iae){
                builder.append(iae.getMessage())
                        .append(System.lineSeparator());
            }
        }
        return builder.toString().trim();
    }
}
