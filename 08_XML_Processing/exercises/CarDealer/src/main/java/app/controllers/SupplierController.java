package app.controllers;

import app.domain.xml_dto.p00_import_data.WrapperImportSupplierDto;
import app.domain.xml_dto.p03_local_suppliers.WrapperSuppliersDto;
import app.services.contracts.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component
public class SupplierController extends BaseController{

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public String importFromXmlString(String jsonString){
        WrapperImportSupplierDto suppliersDtos =
                null;
        try {
            suppliersDtos = super.xmlParser.importXml(WrapperImportSupplierDto.class, jsonString);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        this.supplierService.create(suppliersDtos);
        return "Successfully import suppliers!";
    }

    public String getLocalSuppliers(){
        WrapperSuppliersDto suppliersDto = this.supplierService.getLocalSuppliers();
        String xmlString = null;
        try {
            xmlString = this.xmlParser.exportXml(suppliersDto);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xmlString;
    }

}
