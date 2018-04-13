package app.domain.xml_dto.p00_import_data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPartFromXmlDto {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "price")
    private BigDecimal price;

    @XmlAttribute(name = "quantity")
    private int quantity;



    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
