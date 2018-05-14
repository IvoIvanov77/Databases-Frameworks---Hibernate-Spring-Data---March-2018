package org.softuni.mostwanted.services.impl;


import org.softuni.mostwanted.domain.dto.json_import_dto.CarImportJsonDto;
import org.softuni.mostwanted.domain.dto.xml_import_dto.RaceEntryXMLImportDto;
import org.softuni.mostwanted.domain.models.Car;
import org.softuni.mostwanted.domain.models.RaceEntry;
import org.softuni.mostwanted.domain.models.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.api.RaceEntryService;
import org.springframework.stereotype.Service;

@Service
public class RaceEntryServiceImpl implements RaceEntryService{

    private final ModelParser modelParser;
    private final RacerRepository racerRepository;
    private final CarRepository carRepository;
    private final RaceEntryRepository raceEntryRepository;

    public RaceEntryServiceImpl(ModelParser modelParser, RacerRepository racerRepository,
                                CarRepository carRepository,
                                RaceEntryRepository raceEntryRepository) {
        this.modelParser = modelParser;
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
        this.raceEntryRepository = raceEntryRepository;
    }


    @Override
    public Integer create(RaceEntryXMLImportDto dto){
        RaceEntry raceEntry = this.modelParser.convert(dto, RaceEntry.class);

        Car car = null;
        if(dto.getCarId() != null){
            car = this.carRepository.findOne(dto.getCarId());
        }
        Racer racer = null;
        if(dto.getRacerName() != null){
            racer = this.racerRepository.findFirstByName(dto.getRacerName());
        }
        raceEntry.setCar(car);
        raceEntry.setRacer(racer);
        this.raceEntryRepository.saveAndFlush(raceEntry);
        return raceEntry.getId();
    }
}
