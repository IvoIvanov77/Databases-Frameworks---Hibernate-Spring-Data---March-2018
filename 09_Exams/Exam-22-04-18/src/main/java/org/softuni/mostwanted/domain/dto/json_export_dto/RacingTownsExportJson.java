package org.softuni.mostwanted.domain.dto.json_export_dto;

import com.google.gson.annotations.Expose;

public class RacingTownsExportJson {

    @Expose
    private String name;

    @Expose
    private int racers;

    public RacingTownsExportJson() {
    }

    public RacingTownsExportJson(String name, int racers) {
        this.name = name;
        this.racers = racers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRacers() {
        return racers;
    }

    public void setRacers(int racers) {
        this.racers = racers;
    }
}
