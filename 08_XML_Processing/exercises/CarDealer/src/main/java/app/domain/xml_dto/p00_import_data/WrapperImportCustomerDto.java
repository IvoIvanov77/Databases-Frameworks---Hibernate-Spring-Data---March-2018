package app.domain.xml_dto.p00_import_data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WrapperImportCustomerDto {

    @XmlElement(name = "customer")
    List<ImportCustomerFromXmlDto> customerFromXmlDtos;

    public List<ImportCustomerFromXmlDto> getCustomerFromXmlDtos() {
        return customerFromXmlDtos;
    }

    public void setCustomerFromXmlDtos(List<ImportCustomerFromXmlDto> customerFromXmlDtos) {
        this.customerFromXmlDtos = customerFromXmlDtos;
    }
}
