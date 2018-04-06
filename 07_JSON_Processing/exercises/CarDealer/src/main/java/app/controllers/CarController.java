package app.controllers;

import app.domain.json_dto.p00_import_data.ImportCarFromJsonDto;
import app.domain.json_dto.p01_ordered_customers.OrderedCustomerDto;
import app.domain.json_dto.p02_cars_from_make_toyota.ExportCarDto;
import app.domain.json_dto.p04_cars_with_their_list_of_parts.CarDto;
import app.domain.json_dto.p04_cars_with_their_list_of_parts.ExportCarWithPartsDto;
import app.services.contracts.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarController extends BaseController{

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    public String importFromJsonString(String jsonString){
        ImportCarFromJsonDto[] carsDto =
                this.jsonParser.importJson(ImportCarFromJsonDto[].class, jsonString);
        this.carService.create(carsDto);
        return "Successfully import cars!";
    }

    public String carsFromMake(String make){
        List<ExportCarDto> resultList = this.carService.carsFromMake(make);
        String jsonString = this.jsonParser.exportJson(resultList);
        return jsonString;
    }

    public String carsWithParts(){
        List<ExportCarWithPartsDto> resultList = this.carService.getCarsWithTheirParts();
        String jsonString = this.jsonParser.exportJson(resultList);
        return jsonString;
    }

}
