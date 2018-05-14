package app.controller;

import app.model.dtos.xml_import.WrapperAccessoriesDto;
import app.parser.api.Serializer;
import app.service.contracts.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AccessoryController {

    private final AccessoryService accessoryService;
    private final Serializer xmlParser;

    @Autowired
    public AccessoryController(AccessoryService accessoryService,
                               @Qualifier("XMLParser") Serializer xmlParser) {
        this.accessoryService = accessoryService;

        this.xmlParser = xmlParser;
    }

    public String importAccessoriesFromXml(String xmlString){
        WrapperAccessoriesDto accessoriesDto = null;
        try {
            accessoriesDto =
                    this.xmlParser.deserialize(WrapperAccessoriesDto.class, xmlString);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }

        return this.accessoryService.importAccessoryFromXml(accessoriesDto);
    }
}
