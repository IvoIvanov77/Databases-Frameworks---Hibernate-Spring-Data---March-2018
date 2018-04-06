package app.services.contracts;

import app.domain.json_dto.p00_import_data.ImportSupplierFromJsonDto;
import app.domain.json_dto.p03_local_suppliers.ExportSupplierDto;

import java.util.List;

public interface SupplierService {


    void create(ImportSupplierFromJsonDto[] suppliersDto);

    List<ExportSupplierDto> getLocalSuppliers();
}
