package app.services.contracts;

import app.domain.xml_dto.p00_import_data.WrapperImportPartDto;

public interface PartService {

    void create(WrapperImportPartDto partDtos);
}
