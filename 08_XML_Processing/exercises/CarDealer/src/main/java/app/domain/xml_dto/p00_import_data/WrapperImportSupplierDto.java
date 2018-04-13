package app.domain.xml_dto.p00_import_data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperImportSupplierDto {

    @XmlElement(name = "supplier")
    private List<ImportSupplierFromXmlDto> importSupplierDtos;

    public WrapperImportSupplierDto() {
    }

    public List<ImportSupplierFromXmlDto> getImportSupplierDtos() {
        return importSupplierDtos;
    }

    public void setImportSupplierDtos(List<ImportSupplierFromXmlDto> importSupplierDtos) {
        this.importSupplierDtos = importSupplierDtos;
    }
}
