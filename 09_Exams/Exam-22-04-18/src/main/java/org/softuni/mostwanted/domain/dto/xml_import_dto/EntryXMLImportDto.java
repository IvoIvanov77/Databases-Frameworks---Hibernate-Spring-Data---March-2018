package org.softuni.mostwanted.domain.dto.xml_import_dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryXMLImportDto {
    @NotNull
    @XmlAttribute(name = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
