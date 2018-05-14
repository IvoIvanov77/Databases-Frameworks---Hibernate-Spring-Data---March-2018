package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.dto.json_export_dto.RacingTownsExportJson;
import org.softuni.mostwanted.domain.dto.xml_import_dto.RaceWrapperImportDto;
import org.softuni.mostwanted.domain.dto.xml_import_dto.RaceXMLImportDto;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.RaceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class RaceController {

    private final RaceService raceService;
    private final Parser xmlParser;
    private final Parser jsonPraser;

    public RaceController(RaceService raceService, @Qualifier("XMLParser") Parser xmlParser,
                          @Qualifier("JSONParser") Parser jsonPraser) {
        this.raceService = raceService;
        this.xmlParser = xmlParser;
        this.jsonPraser = jsonPraser;
    }


    public String importRaceFromXml(String jsonString) {
        StringBuilder builder = new StringBuilder();

        RaceWrapperImportDto racesDto = new RaceWrapperImportDto();
        int counter = 1;
        try {
            racesDto = this.xmlParser.read(RaceWrapperImportDto.class, jsonString);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        for (RaceXMLImportDto dto : racesDto.getRaces()) {
            if(ValidationUtil.isValid(dto)){
                this.raceService.create(dto);
                builder.append(String.format("Successfully imported Race - %d.",counter++))
                        .append(System.lineSeparator());
            }else{
                builder.append("Error: Incorrect Data!").append(System.lineSeparator());
            }
        }

        return builder.toString().trim();
    }

    public String exportRacingTowns(){
        List<RacingTownsExportJson> racingTownsExportJsonList = this.raceService.getRacingTowns();
        try {
            return this.jsonPraser.write(racingTownsExportJsonList);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
