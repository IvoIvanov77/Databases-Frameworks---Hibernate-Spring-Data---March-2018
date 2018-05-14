package com.masdefect.controller;

import com.masdefect.domain.dto.json.AnomalyImportJSONDto;
import com.masdefect.domain.dto.json.AnomalyVictimsJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.AnomalyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AnomalyVictimsController {

    @Autowired
    private AnomalyService anomalyService;
    @Autowired
    @Qualifier("JSONParser")
    private FileParser fileParser;


    public String importDataFromJSON(String fileContent){
        AnomalyVictimsJSONDto[] dtos = new AnomalyVictimsJSONDto[0];
        try {
            dtos = this.fileParser.read(AnomalyVictimsJSONDto[].class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();

        for (AnomalyVictimsJSONDto dto : dtos) {
            try{
                this.anomalyService.create(dto);
                builder.append("Successfully imported AnomalyVictims.")
                        .append(System.lineSeparator());
            }catch (IllegalArgumentException iae){
                builder.append(iae.getMessage())
                        .append(System.lineSeparator());
            }
        }
        return builder.toString().trim();
    }
}
