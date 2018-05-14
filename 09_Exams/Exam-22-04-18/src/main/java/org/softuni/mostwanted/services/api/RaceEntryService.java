package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.xml_import_dto.RaceEntryXMLImportDto;

public interface RaceEntryService {
    Integer create(RaceEntryXMLImportDto dto);
}
