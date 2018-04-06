package app.services.impl;

import app.domain.json_dto.p00_import_data.ImportPartFromJsonDto;
import app.domain.models.Part;
import app.domain.models.Supplier;
import app.repositories.PartRepository;
import app.repositories.SupplierRepository;
import app.services.contracts.PartService;
import app.utils.ModelParser;
import app.utils.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void create(ImportPartFromJsonDto[] partsDto){
        List<Supplier> suppliers = this.supplierRepository.findAll();
        List<Part> partForImport = Arrays.stream(partsDto)
                .map(dto ->{
                    Part part = ModelParser.getInstance().map(dto, Part.class);
                    int index = RandomGenerator.getInstance().nextInt(suppliers.size());
                    Supplier supplier = ModelParser.getInstance().map(
                            suppliers.get(index), Supplier.class
                    );
                    part.setSupplier(supplier);
                    return part;
                })
                .collect(Collectors.toList());
        this.partRepository.save(partForImport);
    }
}
