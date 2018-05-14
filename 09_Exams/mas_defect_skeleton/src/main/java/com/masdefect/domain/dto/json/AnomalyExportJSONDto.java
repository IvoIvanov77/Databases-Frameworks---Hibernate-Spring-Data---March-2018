package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;
import com.masdefect.domain.entities.Anomaly;

import java.io.Serializable;

public class AnomalyExportJSONDto implements Serializable {

    @Expose
    private Long id;

    @Expose
    private PlanetExportJSONDto originPlanet;

    @Expose
    private PlanetExportJSONDto teleportPlanet;

    @Expose
    private Integer victimsCount;

    public AnomalyExportJSONDto() {
    }

    public AnomalyExportJSONDto(Anomaly anomaly) {
        this.id = anomaly.getId();
        this.originPlanet = new PlanetExportJSONDto(anomaly.getOriginPlanet());
        this.teleportPlanet = new PlanetExportJSONDto(anomaly.getTeleportPlanet());
        this.victimsCount = anomaly.getVictims().size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlanetExportJSONDto getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(PlanetExportJSONDto originPlanet) {
        this.originPlanet = originPlanet;
    }

    public PlanetExportJSONDto getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(PlanetExportJSONDto teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public Integer getVictimsCount() {
        return victimsCount;
    }

    public void setVictimsCount(Integer victimsCount) {
        this.victimsCount = victimsCount;
    }
}
