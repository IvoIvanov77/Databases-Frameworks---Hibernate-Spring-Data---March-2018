package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.json_import_dto.TownImportJsonDto;

public interface TownService {
    void create(TownImportJsonDto dto);
}
