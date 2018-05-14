package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.json_import_dto.RacerImportJsonDto;
import org.softuni.mostwanted.domain.models.Racer;
import org.softuni.mostwanted.domain.models.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RacerServiceImpl implements RacerService{

    private final ModelParser modelParser;
    private final TownRepository townRepository;
    private final RacerRepository racerRepository;

    @Autowired
    public RacerServiceImpl(ModelParser modelParser, TownRepository townRepository,
                            RacerRepository racerRepository) {
        this.modelParser = modelParser;
        this.townRepository = townRepository;
        this.racerRepository = racerRepository;
    }

    @Override
    public void create(RacerImportJsonDto dto){
        Racer racer = this.modelParser.convert(dto, Racer.class);
        Town town = null;
        if(dto.getHomeTown() != null){
            town = this.townRepository.findFirstByName(dto.getHomeTown());
        }
        racer.setHomeTown(town);
        this.racerRepository.saveAndFlush(racer);
    }

}
