package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.json_export_dto.RacingTownsExportJson;
import org.softuni.mostwanted.domain.dto.xml_import_dto.RaceXMLImportDto;

import java.util.List;

public interface RaceService {
    void create(RaceXMLImportDto dto);

    List<RacingTownsExportJson> getRacingTowns();
}
