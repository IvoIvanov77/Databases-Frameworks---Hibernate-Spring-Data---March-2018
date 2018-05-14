package app.retake.controllers;

import app.retake.domain.dto.ProcedureWrapperXMLExportDTO;
import app.retake.domain.dto.ProcedureWrapperXMLImportDTO;
import app.retake.domain.dto.ProcedureXMLImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class ProcedureController {

    private final ProcedureService procedureService;
    private final Parser xmlParser;

    public ProcedureController(ProcedureService procedureService,
                               @Qualifier("XMLParser") Parser xmlParser) {
        this.procedureService = procedureService;
        this.xmlParser = xmlParser;
    }


    public String importDataFromXML(String xmlContent){
        StringBuilder builder = new StringBuilder();
        ProcedureWrapperXMLImportDTO wrapperXMLImportDTO = new ProcedureWrapperXMLImportDTO();
        try {
            wrapperXMLImportDTO = this.xmlParser
            .read(ProcedureWrapperXMLImportDTO.class, xmlContent);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        for (ProcedureXMLImportDTO dto : wrapperXMLImportDTO.getProcedures()) {
            try{
                this.procedureService.create(dto);
                builder
                        .append("Record successfully imported.")
                        .append(System.lineSeparator());
            }catch (IllegalArgumentException iae){
                builder.append(iae.getMessage()).append(System.lineSeparator());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return builder.toString().trim();
    }

    public String exportProcedures() throws IOException, JAXBException {
        ProcedureWrapperXMLExportDTO exportDTO = this.procedureService.exportProcedures();
        String xmlString = this.xmlParser.write(exportDTO);
        return xmlString;
    }
}
