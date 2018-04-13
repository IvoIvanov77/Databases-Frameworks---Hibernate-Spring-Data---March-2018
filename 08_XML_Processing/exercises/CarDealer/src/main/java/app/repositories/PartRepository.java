package app.repositories;

import app.domain.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import app.domain.models.Car;
import app.domain.models.Part;

import java.util.Set;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    Integer countPartsBySupplier(Supplier supplier);

    @Query("select p from Part p inner join p.cars as c where c = :car")
    Set<Part> findAllByCar(@Param("car") Car car);

}
