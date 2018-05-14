package app.controller;

import app.model.dtos.json_export.ExportPhotographersToJsonDto;
import app.model.dtos.json_import.ImportPhotographerFromJsonDto;
import app.model.dtos.xml_export.ExportPhotographersToXmlDto;
import app.model.dtos.xml_export.WrapperExportPhotographersDto;
import app.parser.api.Serializer;
import app.service.contracts.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class PhotographerController {

    private final PhotographerService photographerService;
    private final Serializer jsonParser;
    private final Serializer xmlParser;

    @Autowired
    public PhotographerController(PhotographerService photographerService,
                                  @Qualifier("JSONParser") Serializer jsonParser,
                                  @Qualifier("XMLParser") Serializer xmlParser) {
        this.photographerService = photographerService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }

    public String importPhotographersFromJson(String jsonString){
        ImportPhotographerFromJsonDto[] photographerFromJsonDtos =
                new ImportPhotographerFromJsonDto[0];
        try {
            photographerFromJsonDtos =
                    this.jsonParser.deserialize(ImportPhotographerFromJsonDto[].class, jsonString);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }

        return this.photographerService.importPhotographerFromJson(photographerFromJsonDtos);
    }

    public String exportOrderedPhotographerToJsonString(){
        List<ExportPhotographersToJsonDto> photographersDtoList =
                this.photographerService.getOrderedPhotographers();
        String jsonString = null;
        try {
            jsonString = this.jsonParser.serialize(photographersDtoList);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return jsonString;

    }public String exportPhotographersWithSameCameraMake(){
        List<ExportPhotographersToXmlDto> photographersDtoList =
                this.photographerService.getPhotographersWithSameCameraMake();
        WrapperExportPhotographersDto photographersDto = new WrapperExportPhotographersDto();
        photographersDto.setPhotographers(photographersDtoList);
        String jsonString = null;
        try {
            jsonString = this.xmlParser.serialize(photographersDto);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return jsonString;
    }


}
