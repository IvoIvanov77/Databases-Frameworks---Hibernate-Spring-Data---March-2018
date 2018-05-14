package app.model.dtos.xml_import;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "workshops")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WrapperWorkshopsDto {

    @XmlElement(name = "workshop")
    private List<ImportWorkshopFromXmlDto> workshops;

    public List<ImportWorkshopFromXmlDto> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<ImportWorkshopFromXmlDto> workshops) {
        this.workshops = workshops;
    }
}
