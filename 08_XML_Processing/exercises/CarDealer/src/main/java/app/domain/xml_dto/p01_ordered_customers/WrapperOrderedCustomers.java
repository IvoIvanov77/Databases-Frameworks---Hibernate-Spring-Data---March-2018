package app.domain.xml_dto.p01_ordered_customers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperOrderedCustomers {

    @XmlElement(name = "customer")
    private List<XmlOrderedCustomerDto> orderedCustomerDtos;

    public List<XmlOrderedCustomerDto> getOrderedCustomerDtos() {
        return orderedCustomerDtos;
    }

    public void setOrderedCustomerDtos(List<XmlOrderedCustomerDto> orderedCustomerDtos) {
        this.orderedCustomerDtos = orderedCustomerDtos;
    }
}
