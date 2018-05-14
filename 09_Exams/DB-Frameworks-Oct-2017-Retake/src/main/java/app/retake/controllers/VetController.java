package app.retake.controllers;

import app.retake.domain.dto.VetWrapperXMLImportDTO;
import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class VetController {

    private final VetService vetService;
    private final Parser xmlParser;

    public VetController(VetService vetService,
                         @Qualifier("XMLParser") Parser xmlParser) {
        this.vetService = vetService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent){
        StringBuilder builder = new StringBuilder();
        try {
            VetWrapperXMLImportDTO vetsDto = this.xmlParser.read(VetWrapperXMLImportDTO.class, xmlContent);
            for (VetXMLImportDTO dto : vetsDto.getVets()) {
                if(ValidationUtil.isValid(dto)){
                    this.vetService.create(dto);
                    builder.append(String.format("Record %s successfully imported.", dto.getName()))
                            .append(System.lineSeparator());
                }else{
                    builder.append("Error: Invalid data.")
                            .append(System.lineSeparator());
                }
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        return builder.toString().trim();
    }
}
