package org.softuni.mostwanted.domain.dto.xml_import_dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceEntryWrapperImportDto {

    @XmlElement(name = "race-entry")
    private List<RaceEntryXMLImportDto> raceEntries;

    public List<RaceEntryXMLImportDto> getRaceEntries() {
        return raceEntries;
    }

    public void setRaceEntries(List<RaceEntryXMLImportDto> raceEntries) {
        this.raceEntries = raceEntries;
    }
}
