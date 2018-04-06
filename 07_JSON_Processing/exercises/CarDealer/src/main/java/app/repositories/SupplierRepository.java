package app.repositories;

import app.domain.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

//    Get all suppliers that do not import parts from abroad. Get their id,
//  name and the number of parts they can
//    offer to supply.

    List<Supplier> findAllByImporterFalse();

    @Query("select distinct s from Supplier s " +
            "left join fetch s.parts " +
            "where s.importer = false")
    List<Supplier> findAllLocalSuppliers();



}
