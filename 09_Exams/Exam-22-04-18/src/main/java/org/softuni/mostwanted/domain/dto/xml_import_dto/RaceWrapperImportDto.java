package org.softuni.mostwanted.domain.dto.xml_import_dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceWrapperImportDto {


    @XmlElement(name = "race")
    private List<RaceXMLImportDto> races;

    public List<RaceXMLImportDto> getRaces() {
        return races;
    }

    public void setRaces(List<RaceXMLImportDto> races) {
        this.races = races;
    }
}
