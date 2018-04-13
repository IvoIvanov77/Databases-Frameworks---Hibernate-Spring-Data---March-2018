package app.domain.xml_dto.p00_import_data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperImportPartDto {

    @XmlElement(name = "part")
    private List<ImportPartFromXmlDto> partFromXmlDtos;

    public List<ImportPartFromXmlDto> getPartFromXmlDtos() {
        return partFromXmlDtos;
    }

    public void setPartFromXmlDtos(List<ImportPartFromXmlDto> partFromXmlDtos) {
        this.partFromXmlDtos = partFromXmlDtos;
    }
}
