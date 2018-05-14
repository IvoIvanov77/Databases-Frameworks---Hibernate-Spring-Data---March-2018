package org.softuni.mostwanted.domain.dto.json_import_dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DistrictImportJsonDto implements Serializable{

    @NotNull
    @Expose
    private String name;

    @NotNull
    @Expose
    private String townName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
