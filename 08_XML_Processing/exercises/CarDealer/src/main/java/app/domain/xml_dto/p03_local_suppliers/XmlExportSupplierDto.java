package app.domain.xml_dto.p03_local_suppliers;

import app.domain.models.Supplier;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class XmlExportSupplierDto {

    @XmlAttribute(name = "id")
    private Long id;

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "parts-count")
    private Integer partsCount;

    public XmlExportSupplierDto() {
    }

    public XmlExportSupplierDto(Long id, String name, Integer partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
    }

    public XmlExportSupplierDto(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
        this.partsCount = supplier.getParts().size();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(Integer partsCount) {
        this.partsCount = partsCount;
    }
}

