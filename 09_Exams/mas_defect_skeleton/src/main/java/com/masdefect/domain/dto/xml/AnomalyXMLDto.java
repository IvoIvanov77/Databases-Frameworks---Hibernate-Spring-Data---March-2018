package com.masdefect.domain.dto.xml;

import com.masdefect.domain.entities.Anomaly;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "anomaly")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AnomalyXMLDto {

    @XmlAttribute(name = "origin-homePlanet")
    private String originPlanet;

    @XmlAttribute(name = "teleport-homePlanet")
    private String teleportPlanet;

    @XmlElementWrapper(name = "victims")
    private List<VictimXMLDto> victims;

    public AnomalyXMLDto() {
    }

    public AnomalyXMLDto(Anomaly anomaly) {
        this.teleportPlanet = anomaly.getTeleportPlanet().getName();
        this.originPlanet = anomaly.getOriginPlanet().getName();
    }

    public String getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(String originPlanet) {
        this.originPlanet = originPlanet;
    }

    public String getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(String teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public List<VictimXMLDto> getVictims() {
        return victims;
    }

    public void setVictims(List<VictimXMLDto> victims) {
        this.victims = victims;
    }
}
