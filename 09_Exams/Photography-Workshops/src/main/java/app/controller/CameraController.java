package app.controller;

import app.model.dtos.json_import.ImportCameraFromJsonDto;
import app.parser.api.Serializer;
import app.service.contracts.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class CameraController {

    private final CameraService cameraService;
    private final Serializer jsonParser;

    @Autowired
    public CameraController(CameraService cameraService, @Qualifier("JSONParser") Serializer jsonParser) {
        this.cameraService = cameraService;
        this.jsonParser = jsonParser;
    }

    public String importCamerasFromJson(String jsonString){
        ImportCameraFromJsonDto[] cameraFromJsonDtos =
                new ImportCameraFromJsonDto[0];
        try {
            cameraFromJsonDtos = this.jsonParser.deserialize(ImportCameraFromJsonDto[].class, jsonString);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        for (ImportCameraFromJsonDto cameraFromJsonDto : cameraFromJsonDtos) {
            builder.append(this.cameraService.importCameraFromJson(cameraFromJsonDto))
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
