package app.domain.xml_dto.p00_import_data;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperImportCarDto {

    @XmlElement(name = "car")
    private List<ImportCarFromXmlDto> carFromXmlDtos;


    public List<ImportCarFromXmlDto> getCarFromXmlDtos() {
        return carFromXmlDtos;
    }

    public void setCarFromXmlDtos(List<ImportCarFromXmlDto> carFromXmlDtos) {
        this.carFromXmlDtos = carFromXmlDtos;
    }
}
