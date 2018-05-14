package app.controller;

import app.parser.api.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Terminal implements CommandLineRunner {

    private final CameraController cameraController;
    private final PhotographerController photographerController;
    private final AccessoryController accessoryController;
    private final WorkshopController workshopController;
    private final FileIO fileIO;

    @Autowired
    public Terminal(CameraController cameraController, PhotographerController photographerController, AccessoryController accessoryController, WorkshopController workshopController, FileIO fileIO) {
        this.cameraController = cameraController;
        this.photographerController = photographerController;
        this.accessoryController = accessoryController;
        this.workshopController = workshopController;
        this.fileIO = fileIO;
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println(this.importCamerasFromJson());
//        System.out.println(this.importPhotographersFromJson());
//        System.out.println(this.importAccessoriesFromXml());
//        System.out.println(this.importWorkshopsFromXml());
//        System.out.println(this.orderedPhotographersToJson());
        System.out.println(this.photographersWithSameCameraMake());
    }

    private String importCamerasFromJson() throws IOException {
        String jsonString = this.fileIO.read("/files/json/cameras.json");
        return this.cameraController.importCamerasFromJson(jsonString);
    }

    private String importPhotographersFromJson() throws IOException {
        String jsonString = this.fileIO.read("/files/json/photographers.json");
        return this.photographerController.importPhotographersFromJson(jsonString);
    }

    private String importAccessoriesFromXml() throws IOException {
        String xmlString = this.fileIO.read("/files/xml/accessories.xml");
        return this.accessoryController.importAccessoriesFromXml(xmlString);
    }

    private String importWorkshopsFromXml() throws IOException {
        String xmlString = this.fileIO.read("/files/xml/workshops.xml");
        return this.workshopController.importWorkshopsFromXml(xmlString);
    }

    private String orderedPhotographersToJson(){
        return this.photographerController.exportOrderedPhotographerToJsonString();
    }

    private String photographersWithSameCameraMake(){
        return this.photographerController.exportPhotographersWithSameCameraMake();
    }


}
