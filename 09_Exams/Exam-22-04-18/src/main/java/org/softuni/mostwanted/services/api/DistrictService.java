package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.json_import_dto.DistrictImportJsonDto;

public interface DistrictService {
    void create(DistrictImportJsonDto dto);
}
