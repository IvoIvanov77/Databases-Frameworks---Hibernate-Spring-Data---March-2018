package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AnimalAidController {

    private final AnimalAidService animalAidService;
    private final Parser jsonParser;

    public AnimalAidController(AnimalAidService animalAidService,
                               @Qualifier("JSONParser") Parser jsonParser) {
        this.animalAidService = animalAidService;
        this.jsonParser = jsonParser;
    }


    public String importDataFromJSON(String jsonContent) throws IOException {
        StringBuilder builder = new StringBuilder();
        AnimalAidJSONImportDTO[] animals =
                new AnimalAidJSONImportDTO[0];
        try {
            animals = this.jsonParser.read(AnimalAidJSONImportDTO[].class, jsonContent);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        Arrays.stream(animals)
                .collect(Collectors.toMap(
                                AnimalAidJSONImportDTO::getName, AnimalAidJSONImportDTO::getPrice,
                                (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                        ))
        .forEach((k, v) ->{
            try{
                this.animalAidService.create(new AnimalAidJSONImportDTO(k, v));
                builder.append(String.format("Record %s successfully imported. ",
                        k)).append(System.lineSeparator());
            }catch (IllegalArgumentException iae){
                builder.append(iae.getMessage()).append(System.lineSeparator());
            }
        });
        return builder.toString().trim();
    }
}
