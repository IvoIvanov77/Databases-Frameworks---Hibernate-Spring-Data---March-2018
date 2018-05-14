package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.json_import_dto.TownImportJsonDto;
import org.softuni.mostwanted.domain.models.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImpl implements TownService{

    private final ModelParser modelParser;
    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(ModelParser modelParser, TownRepository townRepository) {
        this.modelParser = modelParser;
        this.townRepository = townRepository;
    }

    @Override
    public void create(TownImportJsonDto dto){
        if(this.townRepository.findFirstByName(dto.getName()) != null){
            throw new IllegalArgumentException("Error: Duplicate Data!");
        }
        Town town = this.modelParser.convert(dto, Town.class);
        this.townRepository.saveAndFlush(town);

    }


}
