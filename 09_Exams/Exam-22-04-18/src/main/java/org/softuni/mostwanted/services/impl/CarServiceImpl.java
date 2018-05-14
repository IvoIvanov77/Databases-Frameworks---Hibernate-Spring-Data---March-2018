package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.json_import_dto.CarImportJsonDto;
import org.softuni.mostwanted.domain.models.Car;
import org.softuni.mostwanted.domain.models.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.api.CarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService{

    private final ModelParser modelParser;
    private final RacerRepository racerRepository;
    private final CarRepository carRepository;

    public CarServiceImpl(ModelParser modelParser, RacerRepository racerRepository,
                          CarRepository carRepository) {
        this.modelParser = modelParser;
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
    }


    @Override
    public void create(CarImportJsonDto dto){
        Car car = this.modelParser.convert(dto, Car.class);
        Racer racer = null;
        if(dto.getRacerName() != null){
            racer = this.racerRepository.findFirstByName(dto.getRacerName());
        }
        car.setRacer(racer);
        this.carRepository.saveAndFlush(car);
    }


}
