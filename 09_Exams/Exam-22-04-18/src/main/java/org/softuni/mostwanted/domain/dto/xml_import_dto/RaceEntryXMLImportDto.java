package org.softuni.mostwanted.domain.dto.xml_import_dto;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryXMLImportDto {

    @XmlAttribute(name = "has-finished")
    private Boolean hasFinished;

    @XmlAttribute(name = "finish-time")
    private Float finishTime;

    @XmlAttribute(name = "car-id")
    private Integer carId;

    @XmlElement(name = "racer")
    private String racerName;

    public Boolean getHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(Boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public Float getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Float finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getRacerName() {
        return racerName;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }
}
