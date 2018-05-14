package app.retake.controllers;


import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class AnimalController {


    private final AnimalService animalService;
    private final Parser jsonParser;

    public AnimalController(AnimalService animalService,
                            @Qualifier("JSONParser") Parser jsonParser) {
        this.animalService = animalService;
        this.jsonParser = jsonParser;
    }


    public String importDataFromJSON(String jsonContent) {
        StringBuilder builder = new StringBuilder();
        AnimalJSONImportDTO[] animals =
                new AnimalJSONImportDTO[0];
        try {
            animals = this.jsonParser.read(AnimalJSONImportDTO[].class, jsonContent);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        for (AnimalJSONImportDTO animal : animals) {
            try{
                this.animalService.create(animal);
                builder
                        .append(String.format("Record %s Passport №: %s successfully imported.",
                        animal.getName(), animal.getPassport().getSerialNumber()))
                        .append(System.lineSeparator());
            }catch (IllegalArgumentException iae){
                builder.append(iae.getMessage()).append(System.lineSeparator());
            }
        }
        return builder.toString().trim();
    }

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) {
        List<AnimalsJSONExportDTO> animalsList = this.animalService.findByOwnerPhoneNumber(phoneNumber);
        String jsonString = null;
        try {
            jsonString = this.jsonParser.write(animalsList);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
