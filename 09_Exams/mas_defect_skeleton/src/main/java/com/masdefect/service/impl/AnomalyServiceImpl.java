package com.masdefect.service.impl;

import com.masdefect.domain.dto.json.AnomalyExportJSONDto;
import com.masdefect.domain.dto.json.AnomalyImportJSONDto;
import com.masdefect.domain.dto.json.AnomalyVictimsJSONDto;
import com.masdefect.domain.dto.xml.AnomaliesXMLDto;
import com.masdefect.domain.dto.xml.AnomalyXMLDto;
import com.masdefect.domain.dto.xml.VictimXMLDto;
import com.masdefect.domain.entities.Anomaly;
import com.masdefect.domain.entities.Person;
import com.masdefect.domain.entities.Planet;
import com.masdefect.repository.AnomalyRepository;
import com.masdefect.repository.PersonRepository;
import com.masdefect.repository.PlanetRepository;
import com.masdefect.service.AnomalyService;
import com.masdefect.utils.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnomalyServiceImpl implements AnomalyService{

    private final AnomalyRepository anomalyRepository;
    private final PlanetRepository planetRepository;
    private final PersonRepository personRepository;

    @Autowired
    public AnomalyServiceImpl(AnomalyRepository anomalyRepository,
                              PlanetRepository planetRepository,
                              PersonRepository personRepository) {
        this.anomalyRepository = anomalyRepository;
        this.planetRepository = planetRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void create(AnomalyImportJSONDto anomalyImpotJSONDto) {
        Anomaly anomaly = new Anomaly();
        Planet originPlanet = this.planetRepository.findByName(anomalyImpotJSONDto.getOriginPlanet());
        Planet teleportPlanet = this.planetRepository.findByName(anomalyImpotJSONDto.getTeleportPlanet());
        anomaly.setOriginPlanet(originPlanet);
        anomaly.setTeleportPlanet(teleportPlanet);

        if(!DataValidator.isValid(anomaly)){
            throw new IllegalArgumentException("Error: Invalid data.");
        }else{
            this.anomalyRepository.save(anomaly);
        }

    }

    @Override
    public void create(AnomalyVictimsJSONDto anomalyVictimsDto) {
        Anomaly anomaly = this.anomalyRepository.findOne(anomalyVictimsDto.getId());
        Person victim = this.personRepository.findByName(anomalyVictimsDto.getPerson());

        if(!DataValidator.isValid(anomaly) || !DataValidator.isValid(victim)){
            throw new IllegalArgumentException("Error: Invalid data.");
        }else{
            anomaly.getVictims().add(victim);
            victim.getAnomalies().add(anomaly);
            this.anomalyRepository.saveAndFlush(anomaly);
            this.personRepository.saveAndFlush(victim);
        }

    }

    @Override
    public void create(AnomalyXMLDto anomalyImportXMLDto) {
        Anomaly anomaly = new Anomaly();
        anomaly.setOriginPlanet(this.planetRepository.findByName(anomalyImportXMLDto.getOriginPlanet()));
        anomaly.setTeleportPlanet(this.planetRepository.findByName(anomalyImportXMLDto.getTeleportPlanet()));
        Set<Person> victims = new HashSet<>();
        for (VictimXMLDto victim : anomalyImportXMLDto.getVictims()) {
            Person person = this.personRepository.findByName(victim.getName());
            if(!DataValidator.isValid(person)){
                throw new IllegalArgumentException("Error: Invalid data.");
            }else{
                victims.add(person);
            }
        }
        anomaly.setVictims(victims);
        if(!DataValidator.isValid(anomaly)){
            throw new IllegalArgumentException("Error: Invalid data.");
        }else{
            this.anomalyRepository.saveAndFlush(anomaly);
        }
    }

    @Override
    public AnomalyExportJSONDto findMostAffectingAnomalies() {
        List<Anomaly> allAnomalies = this.anomalyRepository.findAll();
        Optional<Anomaly> anomaly = allAnomalies.stream()
                .sorted(Comparator.comparing(a -> a.getVictims().size(), Comparator.reverseOrder()))
                .findFirst();
        AnomalyExportJSONDto anomalyExportJSONDto = null;
        if(anomaly.isPresent()){
            anomalyExportJSONDto = new AnomalyExportJSONDto(anomaly.get());
        }
        return anomalyExportJSONDto;
    }

    @Override
    public AnomaliesXMLDto finaAllAnomalies() {
        List<Anomaly> allAnomalies = this.anomalyRepository.findAll();
        allAnomalies.sort(Comparator.comparing(Anomaly::getId));
        List<AnomalyXMLDto> anomalyXMLDtos = allAnomalies.stream()
                .map(AnomalyXMLDto::new)
                .collect(Collectors.toList());
        AnomaliesXMLDto anomaliesXMLDto = new AnomaliesXMLDto(anomalyXMLDtos);
        return anomaliesXMLDto;
    }
}
