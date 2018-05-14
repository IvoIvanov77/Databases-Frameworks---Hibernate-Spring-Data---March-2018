package app.model.dtos.xml_import;

import app.model.entities.Photographer;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ImportWorkshopFromXmlDto {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "start-date")
    private Date startDate;

    @XmlAttribute(name = "end-date")
    private Date endDate;

    @XmlAttribute(name = "location")
    private String location;

    @XmlAttribute(name = "pricePerParticipant")
    private BigDecimal pricePerParticipant;

    @XmlElement(name = "trainer")
    private String trainer;

    @XmlElementWrapper(name = "participants")
    @XmlElement(name = "participant")
    private List<ParticipantDto> participants;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPricePerParticipant() {
        return pricePerParticipant;
    }

    public void setPricePerParticipant(BigDecimal pricePerParticipant) {
        this.pricePerParticipant = pricePerParticipant;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public List<ParticipantDto> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantDto> participants) {
        this.participants = participants;
    }
}
