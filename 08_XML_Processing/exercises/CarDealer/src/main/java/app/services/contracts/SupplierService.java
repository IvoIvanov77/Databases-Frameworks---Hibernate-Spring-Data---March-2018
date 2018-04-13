package app.services.contracts;

import app.domain.xml_dto.p00_import_data.WrapperImportSupplierDto;
import app.domain.xml_dto.p03_local_suppliers.WrapperSuppliersDto;

public interface SupplierService {


    void create(WrapperImportSupplierDto suppliersDto);

    WrapperSuppliersDto getLocalSuppliers();
}
