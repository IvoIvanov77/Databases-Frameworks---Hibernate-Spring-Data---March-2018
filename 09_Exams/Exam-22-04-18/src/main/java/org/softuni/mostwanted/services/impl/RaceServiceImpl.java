package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.json_export_dto.RacingTownsExportJson;
import org.softuni.mostwanted.domain.dto.xml_import_dto.EntryXMLImportDto;
import org.softuni.mostwanted.domain.dto.xml_import_dto.RaceXMLImportDto;
import org.softuni.mostwanted.domain.models.District;
import org.softuni.mostwanted.domain.models.Race;
import org.softuni.mostwanted.domain.models.RaceEntry;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.repositories.RaceRepository;
import org.softuni.mostwanted.services.api.RaceService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RaceServiceImpl implements RaceService {

    private final ModelParser modelParser;
    private final RaceEntryRepository raceEntryRepository;
    private final DistrictRepository districtRepository;
    private final RaceRepository raceRepository;

    public RaceServiceImpl(ModelParser modelParser, RaceEntryRepository raceEntryRepository,
                           DistrictRepository districtRepository, RaceRepository raceRepository) {
        this.modelParser = modelParser;
        this.raceEntryRepository = raceEntryRepository;
        this.districtRepository = districtRepository;
        this.raceRepository = raceRepository;
    }


    @Override
    public void create(RaceXMLImportDto dto){
        Race race = this.modelParser.convert(dto, Race.class);

        District district = null;
        if(dto.getDistrictName() != null){
            district = this.districtRepository.findFirstByName(dto.getDistrictName());
        }
        Set<RaceEntry> raceEntries = new HashSet<>();
        if(dto.getEntries() != null){
            for (EntryXMLImportDto raceEntryDto : dto.getEntries()) {
                RaceEntry raceEntry = this.raceEntryRepository.findOne(raceEntryDto.getId());
                raceEntries.add(raceEntry);
            }
        }
        race.setDistrict(district);
        race.setEntries(raceEntries);
        this.raceRepository.saveAndFlush(race);

    }

    @Override
    public List<RacingTownsExportJson> getRacingTowns(){
        List<Race> allRaces = this.raceRepository.findAll();
        Map<String, Integer> townMap = new HashMap<>();

        for (Race r : allRaces) {
            String townName = r.getDistrict().getTown().getName();
            if(!townMap.containsKey(townName)){
                townMap.put(townName, this.countOfRaces(allRaces, townName));
            }
        }



        List<RacingTownsExportJson> racingTowns = new ArrayList<>();

        for (String s : townMap.keySet()) {
            racingTowns.add(new RacingTownsExportJson(s, townMap.get(s)));
        }

        return racingTowns;
    }

    private int countOfRaces(List<Race> allRaces, String townName){
        int counter = 0;
        for (Race r : allRaces) {
            if(r.getDistrict().getTown().getName() == townName){
                counter ++;
            }
        }
        return counter;
    }
}
