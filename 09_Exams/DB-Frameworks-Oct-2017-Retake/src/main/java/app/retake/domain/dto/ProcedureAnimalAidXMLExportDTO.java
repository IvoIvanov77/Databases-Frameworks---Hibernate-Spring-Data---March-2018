package app.retake.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProcedureAnimalAidXMLExportDTO {

    //    <procedure animal-passport="jessiii355">
//        <owner>0887446123</owner>
//        <animal-aids>
//            <animal-aid name="Lepto 4" price="15.9"/>
//            <animal-aid name="Lyme Test" price="15.0"/>
//            <animal-aid name="Bordetella" price="7.55"/>
//            <animal-aid name="Fecal Test" price="7.5"/>
//        </animal-aids>
//    </procedure>


    @XmlAttribute
    private String name;

    @XmlAttribute
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
