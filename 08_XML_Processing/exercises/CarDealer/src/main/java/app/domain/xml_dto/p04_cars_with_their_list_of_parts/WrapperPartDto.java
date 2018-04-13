package app.domain.xml_dto.p04_cars_with_their_list_of_parts;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WrapperPartDto {

    @XmlElement(name = "part")
    private List<XmlPartDto> partDtos;

    public WrapperPartDto() {
    }

    public WrapperPartDto(List<XmlPartDto> partDtos) {
        this.partDtos = partDtos;
    }

    public List<XmlPartDto> getPartDtos() {
        return partDtos;
    }

    public void setPartDtos(List<XmlPartDto> partDtos) {
        this.partDtos = partDtos;
    }
}
