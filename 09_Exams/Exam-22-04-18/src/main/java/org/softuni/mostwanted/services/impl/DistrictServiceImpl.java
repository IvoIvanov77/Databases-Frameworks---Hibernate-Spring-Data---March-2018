package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.json_import_dto.DistrictImportJsonDto;
import org.softuni.mostwanted.domain.models.District;
import org.softuni.mostwanted.domain.models.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.DistrictService;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl implements DistrictService{

    private final ModelParser modelParser;
    private final TownRepository townRepository;
    private final DistrictRepository districtRepository;

    public DistrictServiceImpl(ModelParser modelParser, TownRepository townRepository,
                               DistrictRepository repository) {
        this.modelParser = modelParser;
        this.townRepository = townRepository;
        this.districtRepository = repository;
    }

    @Override
    public void create(DistrictImportJsonDto dto){
        if(this.districtRepository.findFirstByNameAndTownName(dto.getName(), dto.getTownName()) != null){
            throw new IllegalArgumentException("Error: Duplicate Data!");
        }
        District district = new District();
        Town town = this.townRepository.findFirstByName(dto.getTownName());
        district.setName(dto.getName());
        district.setTown(town);
        this.districtRepository.saveAndFlush(district);
    }
}
