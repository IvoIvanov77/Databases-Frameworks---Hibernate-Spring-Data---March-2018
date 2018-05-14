package com.masdefect.service.impl;

import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.parser.interfaces.ModelParser;
import com.masdefect.repository.SolarSystemRepository;
import com.masdefect.service.SolarSystemService;
import com.masdefect.utils.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SolarSystemServiceImpl implements SolarSystemService {

    private final SolarSystemRepository solarSystemRepository;
    private final ModelParser modelParser;

    @Autowired
    public SolarSystemServiceImpl(SolarSystemRepository solarSystemRepository, ModelParser modelParser) {
        this.solarSystemRepository = solarSystemRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(SolarSystemImportJSONDto solarSystemImportJSONDto) {
        SolarSystem solarSystem = this.modelParser.convert(solarSystemImportJSONDto, SolarSystem.class);
        if(!DataValidator.isValid(solarSystem)){
            throw new IllegalArgumentException("Error: Invalid data.");
        }
        this.solarSystemRepository.saveAndFlush(solarSystem);
    }
}
