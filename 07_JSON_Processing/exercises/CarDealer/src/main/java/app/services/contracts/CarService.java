package app.services.contracts;

import app.domain.json_dto.p00_import_data.ImportCarFromJsonDto;
import app.domain.json_dto.p02_cars_from_make_toyota.ExportCarDto;
import app.domain.json_dto.p04_cars_with_their_list_of_parts.ExportCarWithPartsDto;

import java.util.List;

public interface CarService {


    void create(ImportCarFromJsonDto[] carsDto);

    List<ExportCarDto> carsFromMake(String make);

    List<ExportCarWithPartsDto> getCarsWithTheirParts();
}
