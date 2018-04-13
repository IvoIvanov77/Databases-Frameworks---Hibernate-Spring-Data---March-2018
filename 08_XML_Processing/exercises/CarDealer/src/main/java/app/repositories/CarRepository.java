package app.repositories;

import app.domain.xml_dto.p02_cars_from_make_toyota.XmlExportCarDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import app.domain.models.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    /**
     * Get all cars from given make and order them by model alphabetically
     * and by travelled distance descending.
     * @param  make - String
     * @return List of cars
     */
    @Query("select new app.domain.xml_dto.p02_cars_from_make_toyota.XmlExportCarDto(" +
            "c.id, c.make, c.model, c.travelledDistance) " +
            "from Car c where c.make = :make " +
            "order by c.model asc, c.travelledDistance desc")
    List<XmlExportCarDto> carsByMake(@Param("make") String make);


    @Query("select distinct c from Car c left join fetch c.parts")
    List<Car> carsWithParts();



}
