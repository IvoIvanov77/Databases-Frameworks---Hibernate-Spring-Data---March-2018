package app.domain.json_dto.p04_cars_with_their_list_of_parts;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ExportCarWithPartsDto {

    @Expose
    private CarDto car;

    @Expose
    private List<PartDto> parts;

    public CarDto getCarDto() {
        return car;
    }

    public void setCarDto(CarDto carDto) {
        this.car = carDto;
    }

    public List<PartDto> getPartDtos() {
        return parts;
    }

    public void setPartDtos(List<PartDto> partDtos) {
        this.parts = partDtos;
    }
}
