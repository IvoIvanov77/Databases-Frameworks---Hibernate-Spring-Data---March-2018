package app.services.contracts;

import app.domain.json_dto.p00_import_data.ImportPartFromJsonDto;
import app.domain.json_dto.p03_local_suppliers.ExportSupplierDto;

import java.util.List;

public interface PartService {

    void create(ImportPartFromJsonDto[] partsDto);
}
