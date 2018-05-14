package app.model.dtos.xml_import;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "accessories")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WrapperAccessoriesDto {

    @XmlElement(name = "accessory")
    private List<ImportAccessoriesFromXmlDto> accessories;

    public List<ImportAccessoriesFromXmlDto> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<ImportAccessoriesFromXmlDto> accessories) {
        this.accessories = accessories;
    }
}
