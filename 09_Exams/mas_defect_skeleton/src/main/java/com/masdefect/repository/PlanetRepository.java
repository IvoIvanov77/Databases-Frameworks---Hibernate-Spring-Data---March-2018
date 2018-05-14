package com.masdefect.repository;

import com.masdefect.domain.entities.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long>{

    Planet findByName(String name);

    @Query("select p from Planet p " +
            "where p.originPlanetAnomalies is empty")
    List<Planet> findAllByEmptyOriginPlanetAnomalies();

}
