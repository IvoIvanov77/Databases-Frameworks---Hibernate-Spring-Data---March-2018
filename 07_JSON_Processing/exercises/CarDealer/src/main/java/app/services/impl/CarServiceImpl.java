package app.services.impl;

import app.domain.json_dto.p00_import_data.ImportCarFromJsonDto;
import app.domain.json_dto.p02_cars_from_make_toyota.ExportCarDto;
import app.domain.json_dto.p04_cars_with_their_list_of_parts.CarDto;
import app.domain.json_dto.p04_cars_with_their_list_of_parts.ExportCarWithPartsDto;
import app.domain.json_dto.p04_cars_with_their_list_of_parts.PartDto;
import app.domain.models.Car;
import app.domain.models.Part;
import app.repositories.CarRepository;
import app.repositories.PartRepository;
import app.services.contracts.CarService;
import app.utils.ModelParser;
import app.utils.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
    public void create(ImportCarFromJsonDto[] carsDto) {
        List<Part> parts = this.partRepository.findAll();
        List<Car> carsForImport = Arrays.stream(carsDto)
                .map(dto ->{
                    Car car = ModelParser.getInstance().map(dto, Car.class);
                    addPartsToCar(parts, car);
                    return car;
                })
                .collect(Collectors.toList());
        this.carRepository.save(carsForImport);
    }

    @Override
    public List<ExportCarDto> carsFromMake(String make){
        return this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make)
                .stream()
                .map(car -> ModelParser.getInstance().map(car, app.domain.json_dto.p02_cars_from_make_toyota.ExportCarDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<ExportCarWithPartsDto> getCarsWithTheirParts(){
        List<Car> list = this.carRepository.carsWithParts();
        return this.carRepository.carsWithParts().stream()
                .map(car -> {
                    ExportCarWithPartsDto exportCarDto = new ExportCarWithPartsDto();
                    CarDto dto = ModelParser.getInstance().map(car, CarDto.class);
                    exportCarDto.setCarDto(dto);
                    PartDto[] partsDto = ModelParser.getInstance()
                            .map(car.getParts(), PartDto[].class);
                    exportCarDto.setPartDtos(Arrays.asList(partsDto));
                    return exportCarDto;
                })
                .collect(Collectors.toList());
    }



    private List<PartDto> getPartDtosByCar(Car car){
        return this.partRepository.findAllByCar(car)
                .stream()
                .map(part -> ModelParser.getInstance().map(part, PartDto.class))
                .collect(Collectors.toList());
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
