package app.domain.xml_dto.p05_total_sales_by_customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WrapperCustomerStatistic {

    @XmlElement(name = "customer")
    private List<XMLCustomerStatisticDto> customerStatisticDtos;


    public List<XMLCustomerStatisticDto> getCustomerStatisticDtos() {
        return customerStatisticDtos;
    }

    public WrapperCustomerStatistic() {
    }

    public WrapperCustomerStatistic(List<XMLCustomerStatisticDto> customerStatisticDtos) {
        this.customerStatisticDtos = customerStatisticDtos;
    }

    public void setCustomerStatisticDtos(List<XMLCustomerStatisticDto> customerStatisticDtos) {
        this.customerStatisticDtos = customerStatisticDtos;
    }
}
