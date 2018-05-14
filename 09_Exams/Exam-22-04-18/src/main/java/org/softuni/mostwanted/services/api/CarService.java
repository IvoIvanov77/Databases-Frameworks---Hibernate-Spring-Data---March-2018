package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.json_import_dto.CarImportJsonDto;

public interface CarService {
    void create(CarImportJsonDto dto);
}
