package org.softuni.mostwanted.domain.dto.xml_import_dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceXMLImportDto {

    @NotNull
    @XmlElement(name = "laps")
    private int laps;

    @XmlElement(name = "district-name")
    private String districtName;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private Set<EntryXMLImportDto> entries;

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Set<EntryXMLImportDto> getEntries() {
        return entries;
    }

    public void setEntries(Set<EntryXMLImportDto> entries) {
        this.entries = entries;
    }
}
