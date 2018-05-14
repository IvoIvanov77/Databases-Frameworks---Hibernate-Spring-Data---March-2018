package com.masdefect.controller;

import com.masdefect.domain.dto.json.AnomalyExportJSONDto;
import com.masdefect.domain.dto.json.AnomalyImportJSONDto;
import com.masdefect.domain.dto.xml.AnomaliesXMLDto;
import com.masdefect.domain.dto.xml.AnomalyXMLDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.AnomalyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AnomalyController {

    @Autowired
    private AnomalyService anomalyService;
    @Autowired
    @Qualifier("JSONParser")
    private FileParser jsonParser;
    @Autowired
    @Qualifier("XMLParser")
    private FileParser xmlParser;


    public String importDataFromJSON(String fileContent) {
        AnomalyImportJSONDto[] dtos = new AnomalyImportJSONDto[0];
        try {
            dtos = this.jsonParser.read(AnomalyImportJSONDto[].class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();

        for (AnomalyImportJSONDto dto : dtos) {
            try{
                this.anomalyService.create(dto);
                builder.append("Successfully imported Anomaly.")
                        .append(System.lineSeparator());
            }catch (IllegalArgumentException iae){
                builder.append(iae.getMessage())
                        .append(System.lineSeparator());
            }
        }
        return builder.toString().trim();
    }

    public String importDataFromXML(String fileContent) {
        AnomaliesXMLDto dtos = new AnomaliesXMLDto();
        try {
            dtos = this.xmlParser.read(AnomaliesXMLDto.class, fileContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        for (AnomalyXMLDto dto : dtos.getAnomalies()) {
            try{
                this.anomalyService.create(dto);
                builder.append("Successfully imported data.")
                        .append(System.lineSeparator());
            }catch (IllegalArgumentException iae){
                builder.append(iae.getMessage())
                        .append(System.lineSeparator());
            }
        }

        return builder.toString().trim();
    }

    public String findAnomalyWithMostVictims() {
        AnomalyExportJSONDto anomalyExportJSONDto = this.anomalyService
                .findMostAffectingAnomalies();
        String result = "";
        try {
            result = this.jsonParser.write(anomalyExportJSONDto);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String exportAnomaliesOrdered() {
        AnomaliesXMLDto anomaliesXMLDto = this.anomalyService.finaAllAnomalies();
        String result = "";
        try {
            result = this.xmlParser.write(anomaliesXMLDto);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
}
