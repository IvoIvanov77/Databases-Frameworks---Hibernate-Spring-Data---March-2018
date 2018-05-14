package app.controller;

import app.model.dtos.xml_import.WrapperWorkshopsDto;
import app.parser.api.Serializer;
import app.service.contracts.WorkshopService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class WorkshopController {

    private final Serializer xmlParser;
    private final WorkshopService workshopService;

    public WorkshopController(@Qualifier("XMLParser") Serializer xmlParser,
                              WorkshopService workshopService) {
        this.xmlParser = xmlParser;
        this.workshopService = workshopService;
    }

    public String importWorkshopsFromXml(String xmlString){
        WrapperWorkshopsDto workshopsDto = null;
        try {
            workshopsDto =
                    this.xmlParser.deserialize(WrapperWorkshopsDto.class, xmlString);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }

        return this.workshopService.importAccessoryFromXml(workshopsDto);
    }
}
