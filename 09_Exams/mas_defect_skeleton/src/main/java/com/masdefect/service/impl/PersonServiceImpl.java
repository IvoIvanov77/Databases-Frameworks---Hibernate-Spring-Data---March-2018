package com.masdefect.service.impl;

import com.masdefect.domain.dto.json.PersonExportJSONDto;
import com.masdefect.domain.dto.json.PersonImportJSONDto;
import com.masdefect.domain.entities.Person;
import com.masdefect.domain.entities.Planet;
import com.masdefect.repository.PersonRepository;
import com.masdefect.repository.PlanetRepository;
import com.masdefect.service.PersonService;
import com.masdefect.utils.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PlanetRepository planetRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PlanetRepository planetRepository) {
        this.personRepository = personRepository;
        this.planetRepository = planetRepository;
    }

    @Override
    public void create(PersonImportJSONDto personImportJSONDto) {
        Person person = new Person();
        Planet planet = this.planetRepository.findByName(personImportJSONDto.getHomePlanet());
        person.setName(personImportJSONDto.getName());
        person.setHomePlanet(planet);

        if(!DataValidator.isValid(person)){
            throw new IllegalArgumentException("Error: Invalid data.");
        }else{
            this.personRepository.save(person);
        }

    }

    @Override
    public List<PersonExportJSONDto> findInnocentPersons() {
        return null;
    }
}
