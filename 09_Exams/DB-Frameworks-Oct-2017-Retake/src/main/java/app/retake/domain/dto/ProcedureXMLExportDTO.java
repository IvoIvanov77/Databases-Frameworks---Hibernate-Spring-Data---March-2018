package app.retake.domain.dto;

import app.retake.domain.models.Procedure;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProcedureXMLExportDTO {



    @XmlAttribute(name = "animal-passport")
    private String animalPassport;

    @XmlElement(name = "owner")
    private String ownerPhoneNumber;

    @XmlElementWrapper(name = "animal-aids")
    @XmlElement(name = "animal-aid")
    private Set<ProcedureAnimalAidXMLExportDTO> animalAids;

    public ProcedureXMLExportDTO() {
    }

    public ProcedureXMLExportDTO(Procedure procedure) {
        this.animalPassport = procedure.getAnimal().getPassport().getSerialNumber();
        this.ownerPhoneNumber = procedure.getAnimal().getPassport().getOwnerPhoneNumber();
    }

    public String getAnimalPassport() {
        return animalPassport;
    }

    public void setAnimalPassport(String animalPassport) {
        this.animalPassport = animalPassport;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public Set<ProcedureAnimalAidXMLExportDTO> getAnimalAids() {
        return animalAids;
    }

    public void setAnimalAids(Set<ProcedureAnimalAidXMLExportDTO> animalAids) {
        this.animalAids = animalAids;
    }
}
