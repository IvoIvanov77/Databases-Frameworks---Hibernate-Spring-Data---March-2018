package app.repositories;

import app.domain.models.Supplier;
import app.domain.xml_dto.p03_local_suppliers.XmlExportSupplierDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {


    List<Supplier> findAllByImporterFalse();

    @Query("select distinct s from Supplier s " +
            "left join fetch s.parts " +
            "where s.importer = false")
    List<Supplier> findAllLocalSuppliers();

    @Query("select new app.domain.xml_dto.p03_local_suppliers.XmlExportSupplierDto(" +
            "s.id, s.name, s.parts.size)" +
            "from Supplier s " +
            "where s.importer = false")
    List<XmlExportSupplierDto> localSuppliers();



}
