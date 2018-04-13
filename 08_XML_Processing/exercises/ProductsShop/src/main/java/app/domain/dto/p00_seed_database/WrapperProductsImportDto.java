package app.domain.dto.p00_seed_database;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WrapperProductsImportDto {

    @XmlElement(name = "product")
    private List<ImportProductXmlDto> products;

    public List<ImportProductXmlDto> getProducts() {
        return products;
    }

    public void setProducts(List<ImportProductXmlDto> products) {
        this.products = products;
    }
}
