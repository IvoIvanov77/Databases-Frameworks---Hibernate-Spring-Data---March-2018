package app.services.impl;

import app.domain.models.Car;
import app.domain.models.Part;
import app.domain.xml_dto.p00_import_data.WrapperImportCarDto;
import app.domain.xml_dto.p02_cars_from_make_toyota.WrapperCarsByMakeDto;
import app.domain.xml_dto.p02_cars_from_make_toyota.XmlExportCarDto;
import app.domain.xml_dto.p04_cars_with_their_list_of_parts.WrapperCarWithPartsDto;
import app.domain.xml_dto.p04_cars_with_their_list_of_parts.WrapperPartDto;
import app.domain.xml_dto.p04_cars_with_their_list_of_parts.XmlCarDto;
import app.domain.xml_dto.p04_cars_with_their_list_of_parts.XmlPartDto;
import app.repositories.CarRepository;
import app.repositories.PartRepository;
import app.services.contracts.CarService;
import app.utils.ModelParser;
import app.utils.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final PartRepository partRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
    }


    @Override
    public void create(WrapperImportCarDto carDtos) {
        List<Part> parts = this.partRepository.findAll();
        List<Car> carsForImport =carDtos.getCarFromXmlDtos()
                .stream()
                .map(dto ->{
                    Car car = ModelParser.getInstance().map(dto, Car.class);
                    this.addPartsToCar(parts, car);
                    return car;
                })
                .collect(Collectors.toList());
        this.carRepository.save(carsForImport);
    }

    @Override
    public WrapperCarsByMakeDto carsFromMake(String make){
       List<XmlExportCarDto> carDtos = this.carRepository.carsByMake(make);
       return new WrapperCarsByMakeDto(carDtos);
    }


    @Override
    public WrapperCarWithPartsDto getCarsWithTheirParts(){
        List<Car> cars = this.carRepository.carsWithParts();
        List<XmlCarDto> xmlCarDtos = cars.stream()
                .map(car -> {
                    XmlCarDto carDto = ModelParser.getInstance().map(car, XmlCarDto.class);
                    List<XmlPartDto> partDtos = car.getParts().stream()
                            .map(part -> ModelParser.getInstance().map(part, XmlPartDto.class))
                            .collect(Collectors.toList());
                    WrapperPartDto wrapperPartDto = new WrapperPartDto(partDtos);
                    carDto.setPartDtos(wrapperPartDto);
                    return carDto;
                })
                .collect(Collectors.toList());

        return new WrapperCarWithPartsDto(xmlCarDtos);
    }


    private void addPartsToCar(List<Part> parts, Car car) {
        int count = 20 - RandomGenerator.getInstance().nextInt(10);
        Set<Part> partsToAdd = new HashSet<>(count);

        for (int i = 0; i < count; i++) {
            int index = RandomGenerator.getInstance().nextInt(parts.size());
            Part part = ModelParser.getInstance().map(
                    parts.get(index), Part.class
            );
            partsToAdd.add(part);
        }

        car.setParts(partsToAdd);
    }
}
