package com.masdefect.controller;

import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.domain.dto.json.StarImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class StarsController {

    @Autowired
    private StarService starService;
    @Autowired
    @Qualifier("JSONParser")
    private FileParser fileParser;

    public String importDataFromJSON(String fileContent){
        StarImportJSONDto[] dto = new StarImportJSONDto[0];
        try {
            dto = this.fileParser
                    .read(StarImportJSONDto[].class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();
        for (StarImportJSONDto starImportJSONDto : dto) {
            try{
                this.starService.create(starImportJSONDto);
                builder.append(String.format("Successfully imported Star %s.",
                        starImportJSONDto.getName()))
                        .append(System.lineSeparator());
            }catch (IllegalArgumentException iae){
                builder.append(iae.getMessage())
                        .append(System.lineSeparator());
            }
        }
        return builder.toString().trim();
    }

}
