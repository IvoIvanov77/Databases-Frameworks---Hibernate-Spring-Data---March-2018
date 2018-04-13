package app.controllers;

import app.domain.xml_dto.p00_import_data.WrapperImportPartDto;
import app.services.contracts.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component
public class PartController extends BaseController{

    private final PartService partServiceService;

    @Autowired
    public PartController(PartService partServiceService) {
        this.partServiceService = partServiceService;
    }

    @Override
    public String importFromXmlString(String xmlString) {
        WrapperImportPartDto partsDtos =
                null;
        try {
            partsDtos = this.xmlParser.importXml(WrapperImportPartDto.class, xmlString);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        this.partServiceService.create(partsDtos);
        return "Successfully import parts!";
    }



}
