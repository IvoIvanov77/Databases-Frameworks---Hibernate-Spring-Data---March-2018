package org.softuni.mostwanted.terminal;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.controllers.*;
import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private final ConsoleIO consoleIO;
    private final FileIO fileIO;
    private final TownController townController;
    private final DistrictController districtController;
    private final RacerController racerController;
    private final CarController carController;
    private final RaceEntryController raceEntryController;
    private final RaceController raceController;

    @Autowired
    public Terminal(ConsoleIO consoleIO, FileIO fileIO, TownController townController,
                    DistrictController districtController, RacerController racerController,
                    CarController carController, RaceEntryController raceEntryController, RaceController raceController) {
        this.consoleIO = consoleIO;
        this.fileIO = fileIO;
        this.townController = townController;
        this.districtController = districtController;
        this.racerController = racerController;
        this.carController = carController;
        this.raceEntryController = raceEntryController;
        this.raceController = raceController;
    }

    @Override
    public void run(String... args) throws Exception {
        this.consoleIO.write(this.importTowns());
        this.consoleIO.write(this.importDistricts());
        this.consoleIO.write(this.importRacers());
        this.consoleIO.write(this.importCars());
        this.consoleIO.write(this.importRaceEntries());
        this.consoleIO.write(this.importRaces());
//        this.consoleIO.write(this.raceController.exportRacingTowns());

    }

    private String importTowns() throws IOException {
        String jsonContent = this.fileIO.read(Config.TOWNS_IMPORT_JSON);
        return this.townController.importTownsFromJson(jsonContent);
    }

    private String importDistricts() throws IOException {
        String jsonContent = this.fileIO.read(Config.DISTRICTS_IMPORT_JSON);
        return this.districtController.importTownsFromJson(jsonContent);
    }

    private String importRacers() throws IOException {
        String jsonContent = this.fileIO.read(Config.RACERS_IMPORT_JSON);
        return this.racerController.importRacersFromJson(jsonContent);
    }

    private String importCars() throws IOException {
        String jsonContent = this.fileIO.read(Config.CARS_IMPORT_JSON);
        return this.carController.importCarsFromJson(jsonContent);
    }

    private String importRaceEntries() throws IOException {
        String jsonContent = this.fileIO.read(Config.RACE_ENTRIES_IMPORT_XML);
        return this.raceEntryController.importRaceEntriesFromXml(jsonContent);
    }

    private String importRaces() throws IOException {
        String jsonContent = this.fileIO.read(Config.RACE_IMPORT_XML);
        return this.raceController.importRaceFromXml(jsonContent);
    }
}
