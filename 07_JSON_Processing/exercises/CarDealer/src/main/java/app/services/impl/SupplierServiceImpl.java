package app.services.impl;

import app.domain.json_dto.p03_local_suppliers.ExportSupplierDto;
import app.domain.models.Supplier;
import app.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.json_dto.p00_import_data.ImportSupplierFromJsonDto;
import app.services.contracts.SupplierService;
import app.utils.ModelParser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }


    @Override
    public void create(ImportSupplierFromJsonDto[] suppliersDto){
        List<Supplier> suppliers = Arrays.stream(suppliersDto)
                .map(dto -> ModelParser.getInstance().map(dto, Supplier.class))
                .collect(Collectors.toList());
        this.supplierRepository.save(suppliers);
    }

    @Override
    public List<ExportSupplierDto> getLocalSuppliers(){
        List<Supplier> suppliers = this.supplierRepository.findAllLocalSuppliers();
        List<ExportSupplierDto> resultList = suppliers.stream()
                .map(ExportSupplierDto::new)
                .collect(Collectors.toList());
        return resultList;
    }


}
