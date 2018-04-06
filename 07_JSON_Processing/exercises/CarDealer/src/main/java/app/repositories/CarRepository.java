package app.repositories;

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
     * Export the list of cars to JSON in the format provided below.
     * @param  make - String
     * @return List of cars
     */
    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);


    @Query("select distinct c from Car c left join fetch c.parts")
    List<Car> carsWithParts();



}
