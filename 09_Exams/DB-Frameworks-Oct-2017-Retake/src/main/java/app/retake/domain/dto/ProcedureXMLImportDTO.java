package app.retake.domain.dto;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureXMLImportDTO {

    @XmlElement(name = "vet")
    private String vetName;

    @XmlElement(name = "animal")
    private String animalPassportSerialNumber;

    @XmlElement(name = "date")
    private String date;

    @XmlElementWrapper(name = "animal-aids")
    @XmlElement(name = "animal-aid")
    private List<ProcedureAnimalAidXMLImportDTO> animalIds;



    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

    public String getAnimalPassportSerialNumber() {
        return animalPassportSerialNumber;
    }

    public void setAnimalPassportSerialNumber(String animalPassportSerialNumber) {
        this.animalPassportSerialNumber = animalPassportSerialNumber;
    }

    public List<ProcedureAnimalAidXMLImportDTO> getAnimalIds() {
        return animalIds;
    }

    public void setAnimalIds(List<ProcedureAnimalAidXMLImportDTO> animalIds) {
        this.animalIds = animalIds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
