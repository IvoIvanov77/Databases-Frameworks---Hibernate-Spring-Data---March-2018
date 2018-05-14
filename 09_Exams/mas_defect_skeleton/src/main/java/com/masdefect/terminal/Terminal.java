package com.masdefect.terminal;

import com.masdefect.config.Config;
import com.masdefect.controller.*;
import com.masdefect.io.interfaces.ConsoleIO;
import com.masdefect.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    private SolarSystemController solarSystemController;

    @Autowired
    private StarsController starsController;

    @Autowired
    private PlanetsController planetsController;

    @Autowired
    private PersonsController personsController;

    @Autowired
    private AnomalyController anomalyController;

    @Autowired
    private AnomalyVictimsController anomalyVictimsController;

    @Autowired
    private FileIO fileIO;

    @Autowired
    private ConsoleIO consoleIO;

    @Override
    public void run(String... args) throws Exception {
        importSolarSystem();
        importStars();
        importPlanets();
        importPersons();
        importAnomalies();
        importAnomaliesVictims();
        importAnomaliesFromXml();
        exportPlanetToJson();
        exportAnomalyToJson();
        exportAllAnomalyToXml();
    }

    private void importSolarSystem() throws IOException {
        String content = this.fileIO.read(Config.SOLAR_SYSTEM_IMPORT_JSON);
        this.consoleIO.write(this.solarSystemController.importDataFromJSON(content));
    }

    private void importStars() throws IOException {
        String content = this.fileIO.read(Config.STARS_IMPORT_JSON);
        this.consoleIO.write(this.starsController.importDataFromJSON(content));
    }

    private void importPlanets() throws IOException {
        String content = this.fileIO.read(Config.PLANETS_IMPORT_JSON);
        this.consoleIO.write(this.planetsController.importDataFromJSON(content));
    }

    private void importPersons() throws IOException {
        String content = this.fileIO.read(Config.PERSONS_IMPORT_JSON);
        this.consoleIO.write(this.personsController.importDataFromJSON(content));
    }

    private void importAnomalies() throws IOException {
        String content = this.fileIO.read(Config.ANOMALY_IMPORT_JSON);
        this.consoleIO.write(this.anomalyController.importDataFromJSON(content));
    }

    private void importAnomaliesVictims() throws IOException {
        String content = this.fileIO.read(Config.ANOMALY_VICTIMS_IMPORT_JSON);
        this.consoleIO.write(this.anomalyVictimsController.importDataFromJSON(content));
    }

    private void importAnomaliesFromXml() throws IOException {
        String content = this.fileIO.read(Config.ANOMALIES_IMPORT_XML);
        this.consoleIO.write(this.anomalyController.importDataFromXML(content));
    }

    private void exportPlanetToJson() throws IOException {
        String content = this.planetsController.planetsWithNoPeopleTeleportedToThem();
        this.consoleIO.write(content);
    }

    private void exportAnomalyToJson() throws IOException {
        String content = this.anomalyController.findAnomalyWithMostVictims();
        this.consoleIO.write(content);
    }

    private void exportAllAnomalyToXml() throws IOException {
        String content = this.anomalyController.exportAnomaliesOrdered();
        this.consoleIO.write(content);
    }
}
