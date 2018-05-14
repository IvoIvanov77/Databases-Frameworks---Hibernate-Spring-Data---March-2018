package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.dto.json_import_dto.CarImportJsonDto;
import org.softuni.mostwanted.domain.dto.xml_import_dto.RaceEntryWrapperImportDto;
import org.softuni.mostwanted.domain.dto.xml_import_dto.RaceEntryXMLImportDto;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.RaceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceEntryController {

    private final RaceEntryService raceEntryService;
    private final Parser xmlParser;

    @Autowired
    public RaceEntryController(RaceEntryService raceEntryService, @Qualifier("XMLParser") Parser xmlParser) {
        this.raceEntryService = raceEntryService;
        this.xmlParser = xmlParser;
    }


    public String importRaceEntriesFromXml(String jsonString) {
        StringBuilder builder = new StringBuilder();
        int counter = 1;
        RaceEntryWrapperImportDto entriesDto = new RaceEntryWrapperImportDto();
        try {
            entriesDto = this.xmlParser.read(RaceEntryWrapperImportDto.class, jsonString);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        for (RaceEntryXMLImportDto dto : entriesDto.getRaceEntries()) {
            if(ValidationUtil.isValid(dto)){
                this.raceEntryService.create(dto);
                builder.append(String.format("Successfully imported RaceEntry - %d.",counter++))
                        .append(System.lineSeparator());


            }else{
                builder.append("Error: Incorrect Data!").append(System.lineSeparator());
            }
        }

        return builder.toString().trim();
    }
}
