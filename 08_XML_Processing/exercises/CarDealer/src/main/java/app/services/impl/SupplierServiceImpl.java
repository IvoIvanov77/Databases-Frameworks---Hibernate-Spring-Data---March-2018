package app.services.impl;

import app.domain.models.Supplier;
import app.domain.xml_dto.p00_import_data.WrapperImportSupplierDto;
import app.domain.xml_dto.p03_local_suppliers.WrapperSuppliersDto;
import app.domain.xml_dto.p03_local_suppliers.XmlExportSupplierDto;
import app.repositories.SupplierRepository;
import app.services.contracts.SupplierService;
import app.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void create(WrapperImportSupplierDto suppliersDto){
        List<Supplier> suppliers = suppliersDto.getImportSupplierDtos()
                .stream()
                .map(dto -> ModelParser.getInstance().map(dto, Supplier.class))
                .collect(Collectors.toList());
        this.supplierRepository.save(suppliers);
    }

    @Override
    public WrapperSuppliersDto getLocalSuppliers(){
        List<XmlExportSupplierDto> suppliers = this.supplierRepository.localSuppliers();
        return new WrapperSuppliersDto(suppliers);
    }


}
