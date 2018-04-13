package app.domain.xml_dto.p04_cars_with_their_list_of_parts;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WrapperCarWithPartsDto {

    @XmlElement(name = "car")
    private List<XmlCarDto> carDtos;

    public WrapperCarWithPartsDto() {
    }

    public WrapperCarWithPartsDto(List<XmlCarDto> carDtos) {
        this.carDtos = carDtos;
    }

    public List<XmlCarDto> getCarDtos() {
        return carDtos;
    }

    public void setCarDtos(List<XmlCarDto> carDtos) {
        this.carDtos = carDtos;
    }
}
