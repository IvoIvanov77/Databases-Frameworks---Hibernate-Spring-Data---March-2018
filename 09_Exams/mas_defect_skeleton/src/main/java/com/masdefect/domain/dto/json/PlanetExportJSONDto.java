package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;
import com.masdefect.domain.entities.Planet;

import java.io.Serializable;

public class PlanetExportJSONDto implements Serializable {
    //impl

    @Expose
    private String name;

    public PlanetExportJSONDto() {
    }

    public PlanetExportJSONDto(Planet planet) {
        this.name = planet.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
