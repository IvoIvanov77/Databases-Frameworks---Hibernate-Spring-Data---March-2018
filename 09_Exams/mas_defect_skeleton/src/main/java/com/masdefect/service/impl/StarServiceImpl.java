package com.masdefect.service.impl;

import com.masdefect.domain.dto.json.StarImportJSONDto;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.domain.entities.Star;
import com.masdefect.repository.SolarSystemRepository;
import com.masdefect.repository.StarRepository;
import com.masdefect.service.StarService;
import com.masdefect.utils.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarServiceImpl implements StarService{

    private final StarRepository starRepository;
    private final SolarSystemRepository solarSystemRepository;

    @Autowired
    public StarServiceImpl(StarRepository starRepository, SolarSystemRepository solarSystemRepository) {
        this.starRepository = starRepository;
        this.solarSystemRepository = solarSystemRepository;
    }

    @Override
    public void create(StarImportJSONDto starImportJSONDto) {
        Star star = new Star();
        SolarSystem solarSystem = this.solarSystemRepository.findByName(starImportJSONDto.getSolarSystem());
        star.setName(starImportJSONDto.getName());
        star.setSolarSystem(solarSystem);
        if(!DataValidator.isValid(star)){
            throw new IllegalArgumentException("Error: Invalid data.");
        }else{
            this.starRepository.save(star);
        }

    }
}
