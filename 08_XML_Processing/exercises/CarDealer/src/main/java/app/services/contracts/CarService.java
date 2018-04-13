package app.services.contracts;

import app.domain.xml_dto.p00_import_data.WrapperImportCarDto;
import app.domain.xml_dto.p02_cars_from_make_toyota.WrapperCarsByMakeDto;
import app.domain.xml_dto.p04_cars_with_their_list_of_parts.WrapperCarWithPartsDto;

public interface CarService {

    void create(WrapperImportCarDto carDtos);

    WrapperCarsByMakeDto carsFromMake(String make);

    WrapperCarWithPartsDto getCarsWithTheirParts();
}
