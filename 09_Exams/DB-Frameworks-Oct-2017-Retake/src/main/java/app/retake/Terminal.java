package app.retake;

import app.retake.controllers.AnimalAidController;
import app.retake.controllers.AnimalController;
import app.retake.controllers.ProcedureController;
import app.retake.controllers.VetController;
import app.retake.domain.models.Procedure;
import app.retake.io.api.ConsoleIO;
import app.retake.io.api.FileIO;
import app.retake.repositories.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    private final AnimalAidController animalAidController;
    private final AnimalController animalController;
    private final VetController vetController;
    private final ProcedureController procedureController;
    private final FileIO fileIO;
    private final  ConsoleIO consoleIO;


    @Autowired
    public Terminal(AnimalAidController animalAidController, AnimalController animalController,
                    VetController vetController, ProcedureController procedureController, FileIO fileIO,
                    ConsoleIO consoleIO) {
        this.animalAidController = animalAidController;
        this.animalController = animalController;
        this.vetController = vetController;
        this.procedureController = procedureController;
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
    }

    @Override
    public void run(String... strings) throws Exception {
//        consoleIO.write(this.importAnimalAids());
//        consoleIO.write(this.importAnimals());
//        consoleIO.write(this.importVets());
//        consoleIO.write(this.importProcedures());
//        consoleIO.write(this.exportAnimals("0887446123"));

        consoleIO.write(this.exportProcedures());
    }

    private String importAnimalAids() throws IOException {
        String jsonString = this.fileIO.read(Config.ANIMAL_AIDS_IMPORT_JSON);
        return this.animalAidController.importDataFromJSON(jsonString);
    }

    private String importAnimals() throws IOException {
        String jsonString = this.fileIO.read(Config.ANIMALS_IMPORT_JSON);
        return this.animalController.importDataFromJSON(jsonString);
    }

    private String importVets() throws IOException {
        String xmlString = this.fileIO.read(Config.VETS_IMPORT_XML);
        return this.vetController.importDataFromXML(xmlString);
    }

    private String importProcedures() throws IOException {
        String xmlString = this.fileIO.read(Config.PROCEDURES_IMPORT_XML);
        return this.procedureController.importDataFromXML(xmlString);
    }

    private String exportAnimals(String phoneNumber) throws IOException {
        String jsonString = this.animalController.exportAnimalsByOwnerPhoneNumber(phoneNumber);
        return jsonString;
    }
    private String exportProcedures() throws IOException, JAXBException {
        String xmlString = this.procedureController.exportProcedures();
        return xmlString;
    }
}
