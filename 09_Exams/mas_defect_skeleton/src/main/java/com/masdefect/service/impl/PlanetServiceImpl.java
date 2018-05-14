package com.masdefect.service.impl;

import com.masdefect.domain.dto.json.PlanetExportJSONDto;
import com.masdefect.domain.dto.json.PlanetImportJSONDto;
import com.masdefect.domain.dto.json.StarImportJSONDto;
import com.masdefect.domain.entities.Planet;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.domain.entities.Star;
import com.masdefect.repository.PlanetRepository;
import com.masdefect.repository.SolarSystemRepository;
import com.masdefect.repository.StarRepository;
import com.masdefect.service.PlanetService;
import com.masdefect.utils.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanetServiceImpl implements PlanetService{

    private final StarRepository starRepository;
    private final SolarSystemRepository solarSystemRepository;
    private final PlanetRepository planetRepository;

    @Autowired
    public PlanetServiceImpl(StarRepository starRepository,
                             SolarSystemRepository solarSystemRepository,
                             PlanetRepository planetRepository) {
        this.starRepository = starRepository;
        this.solarSystemRepository = solarSystemRepository;
        this.planetRepository = planetRepository;
    }

    @Override
    public void create(PlanetImportJSONDto planetImportJSONDto) {
        Planet planet = new Planet();
        SolarSystem solarSystem = this.solarSystemRepository.findByName(planetImportJSONDto.getSolarSystem());
        Star star = this.starRepository.findByName(planetImportJSONDto.getSun());
        planet.setName(planetImportJSONDto.getName());
        planet.setSolarSystem(solarSystem);
        planet.setSun(star);

        if(!DataValidator.isValid(planet)){
            throw new IllegalArgumentException("Error: Invalid data.");
        }else{
            this.planetRepository.save(planet);
        }
    }

    @Override
    public List<PlanetExportJSONDto> findAllPlanetsWithoutPeopleTeleportedFromThem() {
        List<Planet> planets = this.planetRepository.findAllByEmptyOriginPlanetAnomalies();
        List<PlanetExportJSONDto> exportJSONDtos = planets.stream()
                .map(PlanetExportJSONDto::new)
                .collect(Collectors.toList());
        return exportJSONDtos;
    }
}
