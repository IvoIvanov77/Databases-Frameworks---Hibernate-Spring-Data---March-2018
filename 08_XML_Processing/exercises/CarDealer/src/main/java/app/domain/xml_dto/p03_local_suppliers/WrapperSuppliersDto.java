package app.domain.xml_dto.p03_local_suppliers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "suppliers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WrapperSuppliersDto {

    @XmlElement(name = "supplier")
    private List<XmlExportSupplierDto> supplierDtos;

    public WrapperSuppliersDto() {
    }

    public WrapperSuppliersDto(List<XmlExportSupplierDto> supplierDtos) {
        this.supplierDtos = supplierDtos;
    }

    public List<XmlExportSupplierDto> getSupplierDtos() {
        return supplierDtos;
    }

    public void setSupplierDtos(List<XmlExportSupplierDto> supplierDtos) {
        this.supplierDtos = supplierDtos;
    }
}
