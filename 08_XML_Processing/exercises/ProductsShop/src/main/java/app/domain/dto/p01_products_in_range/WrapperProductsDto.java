package app.domain.dto.p01_products_in_range;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperProductsDto {

    @XmlElement(name = "product")
    private List<ExportProductInRangeDto> products;

    public List<ExportProductInRangeDto> getProducts() {
        return products;
    }

    public void setProducts(List<ExportProductInRangeDto> products) {
        this.products = products;
    }
}
