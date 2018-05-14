package app.retake.services.impl;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import app.retake.parser.ModelParserImpl;
import app.retake.parser.ValidationUtil;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.PassportRepository;
import app.retake.services.api.AnimalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final PassportRepository passportRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository, PassportRepository passportRepository) {
        this.animalRepository = animalRepository;
        this.passportRepository = passportRepository;
    }

    @Override
    public void create(AnimalJSONImportDTO dto)  {
       Animal animal = ModelParserImpl.getInstance().map(dto, Animal.class);
       animal.getPassport().setAnimal(animal);
       if(ValidationUtil.isValid(animal) && ValidationUtil.isValid(animal.getPassport())){
           if(!this.passportRepository.exists(animal.getPassport().getSerialNumber())){
               this.animalRepository.saveAndFlush(animal);
           }

       }else {
           throw new IllegalArgumentException("Error: Invalid data.");
       }
    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {

        return this.animalRepository.allAnimalsByOwnerPhoneNumber(phoneNumber);
    }
}
