package app.controllers;

import app.domain.json_dto.p00_import_data.ImportSupplierFromJsonDto;
import app.domain.json_dto.p02_cars_from_make_toyota.ExportCarDto;
import app.domain.json_dto.p03_local_suppliers.ExportSupplierDto;
import app.services.contracts.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SupplierController extends BaseController{

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public String importFromJsonString(String jsonString){
        ImportSupplierFromJsonDto[] suppliersDto =
                super.jsonParser.importJson(ImportSupplierFromJsonDto[].class, jsonString);
        this.supplierService.create(suppliersDto);
        return "Successfully import suppliers!";
    }

    public String getLocalSuppliers(){
        List<ExportSupplierDto> resultList = this.supplierService.getLocalSuppliers();
        String jsonString = this.jsonParser.exportJson(resultList);
        return jsonString;
    }

}
