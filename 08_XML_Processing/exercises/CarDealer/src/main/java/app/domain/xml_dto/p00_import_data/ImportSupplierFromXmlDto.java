package app.domain.xml_dto.p00_import_data;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportSupplierFromXmlDto {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "is-importer")
    private boolean isImporter;

    public ImportSupplierFromXmlDto() {
    }

    public ImportSupplierFromXmlDto(String name, boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
