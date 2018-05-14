package app.model.dtos.xml_export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "photographers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WrapperExportPhotographersDto {

    @XmlElement(name = "photographer")
    private List<ExportPhotographersToXmlDto> photographers;

    public List<ExportPhotographersToXmlDto> getPhotographers() {
        return photographers;
    }

    public void setPhotographers(List<ExportPhotographersToXmlDto> photographers) {
        this.photographers = photographers;
    }
}
