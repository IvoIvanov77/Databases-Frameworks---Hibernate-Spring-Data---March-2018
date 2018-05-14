package org.softuni.mostwanted.domain.dto.json_import_dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownImportJsonDto {

    @NotNull
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
