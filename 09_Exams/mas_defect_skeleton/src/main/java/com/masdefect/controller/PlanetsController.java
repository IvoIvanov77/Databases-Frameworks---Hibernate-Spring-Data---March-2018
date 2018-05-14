package com.masdefect.controller;

import com.masdefect.domain.dto.json.PlanetExportJSONDto;
import com.masdefect.domain.dto.json.PlanetImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class PlanetsController {

    @Autowired
    private PlanetService planetService;
    @Autowired
    @Qualifier("JSONParser")
    private FileParser fileParser;


    public String importDataFromJSON(String fileContent){
        PlanetImportJSONDto[] dto = new PlanetImportJSONDto[0];

        try {
            dto = this.fileParser.read(PlanetImportJSONDto[].class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();

        for (PlanetImportJSONDto planetImportJSONDto : dto) {
            try{
                this.planetService.create(planetImportJSONDto);
                builder.append(String.format("Successfully imported Planet %s.",
                        planetImportJSONDto.getName()))
                        .append(System.lineSeparator());
            }catch (IllegalArgumentException iae){
                builder.append(iae.getMessage())
                        .append(System.lineSeparator());
            }
        }
        return builder.toString().trim();
    }

    public String planetsWithNoPeopleTeleportedToThem(){
        List<PlanetExportJSONDto> planetExportJSONDtos = this.planetService
                .findAllPlanetsWithoutPeopleTeleportedFromThem();
        String result = "";
        try {
            result = this.fileParser.write(planetExportJSONDtos);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
}
