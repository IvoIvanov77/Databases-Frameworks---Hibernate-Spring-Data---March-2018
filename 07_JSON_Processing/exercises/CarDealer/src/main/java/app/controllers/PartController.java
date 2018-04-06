package app.controllers;

import app.domain.json_dto.p00_import_data.ImportPartFromJsonDto;
import app.domain.json_dto.p00_import_data.ImportSupplierFromJsonDto;
import app.services.contracts.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartController extends BaseController{

    private final PartService partServiceService;

    @Autowired
    public PartController(PartService partServiceService) {
        this.partServiceService = partServiceService;
    }

    public String importFromJsonString(String jsonString){
        ImportPartFromJsonDto[] partsDto =
                this.jsonParser.importJson(ImportPartFromJsonDto[].class, jsonString);
        this.partServiceService.create(partsDto);
        return "Successfully import parts!";
    }

}
