package app.domain.xml_dto.p06_sales_with_applied_discount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WrapperCarWithDiscountDtos {

    @XmlElement(name = "sale")
    private List<XmlSaleWithDiscountDto> saleWithDiscountDtos;

    public WrapperCarWithDiscountDtos() {
    }

    public WrapperCarWithDiscountDtos(List<XmlSaleWithDiscountDto> saleWithDiscountDtos) {
        this.saleWithDiscountDtos = saleWithDiscountDtos;
    }

    public List<XmlSaleWithDiscountDto> getSaleWithDiscountDtos() {
        return saleWithDiscountDtos;
    }

    public WrapperCarWithDiscountDtos setSaleWithDiscountDtos(List<XmlSaleWithDiscountDto> saleWithDiscountDtos) {
        this.saleWithDiscountDtos = saleWithDiscountDtos;
        return this;
    }
}
