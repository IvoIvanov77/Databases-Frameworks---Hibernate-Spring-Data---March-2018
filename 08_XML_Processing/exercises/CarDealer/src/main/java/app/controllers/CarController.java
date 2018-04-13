package app.controllers;

import app.domain.xml_dto.p00_import_data.WrapperImportCarDto;
import app.domain.xml_dto.p02_cars_from_make_toyota.WrapperCarsByMakeDto;
import app.domain.xml_dto.p04_cars_with_their_list_of_parts.WrapperCarWithPartsDto;
import app.services.contracts.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component
public class CarController extends BaseController{

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Override
    public String importFromXmlString(String xmlString) {
        WrapperImportCarDto carDtos =
                null;
        try {
            carDtos = this.xmlParser.importXml(WrapperImportCarDto.class, xmlString);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        this.carService.create(carDtos);
        return "Successfully import cars!";
    }


    public String carsFromMake(String make){
        WrapperCarsByMakeDto carsDto = this.carService.carsFromMake(make);
        String xmlString = null;
        try {
            xmlString = this.xmlParser.exportXml(carsDto);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xmlString;
    }

    public String carsWithParts(){
        WrapperCarWithPartsDto cars = this.carService.getCarsWithTheirParts();
        String xmlString = null;
        try {
            xmlString = this.xmlParser.exportXml(cars);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xmlString;
    }

}
