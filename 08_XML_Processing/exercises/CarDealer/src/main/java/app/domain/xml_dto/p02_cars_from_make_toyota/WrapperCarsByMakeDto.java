package app.domain.xml_dto.p02_cars_from_make_toyota;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "cars")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WrapperCarsByMakeDto {

    @XmlElement(name = "car")
    private List<XmlExportCarDto> carDtos;

    public WrapperCarsByMakeDto() {
    }

    public WrapperCarsByMakeDto(List<XmlExportCarDto> carDtos) {
        this.carDtos = carDtos;
    }

    public List<XmlExportCarDto> getCarDtos() {
        return carDtos;
    }

    public void setCarDtos(List<XmlExportCarDto> carDtos) {
        this.carDtos = carDtos;
    }
}
