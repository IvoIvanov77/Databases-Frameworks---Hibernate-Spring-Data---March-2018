package com.masdefect.controller;

import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.SolarSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class SolarSystemController {

    @Autowired
    private SolarSystemService solarSystemService;
    @Autowired
    @Qualifier("JSONParser")
    private FileParser fileParser;

    public String importDataFromJSON(String fileContent){
        SolarSystemImportJSONDto[] dto = new SolarSystemImportJSONDto[0];
        try {
            dto = this.fileParser
                    .read(SolarSystemImportJSONDto[].class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();
        for (SolarSystemImportJSONDto solarSystemImportJSONDto : dto) {
            try{
                this.solarSystemService.create(solarSystemImportJSONDto);
                builder.append(String.format("Successfully imported Solar System %s.",
                        solarSystemImportJSONDto.getName()))
                        .append(System.lineSeparator());
            }catch (IllegalArgumentException iae){
                builder.append(iae.getMessage())
                        .append(System.lineSeparator());
            }
        }
        return builder.toString().trim();
    }
}
