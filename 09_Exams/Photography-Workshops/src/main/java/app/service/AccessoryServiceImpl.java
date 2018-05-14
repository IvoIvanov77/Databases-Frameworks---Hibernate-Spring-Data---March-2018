package app.service;

import app.model.dtos.json_import.ImportPhotographerFromJsonDto;
import app.model.dtos.xml_import.ImportAccessoriesFromXmlDto;
import app.model.dtos.xml_import.WrapperAccessoriesDto;
import app.model.entities.Accessory;
import app.model.entities.BasicCamera;
import app.model.entities.Photographer;
import app.repositories.AccessoryRepository;
import app.repositories.PhotographerRepository;
import app.service.contracts.AccessoryService;
import app.utils.ModelParser;
import app.utils.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessoryServiceImpl implements AccessoryService{

    private final AccessoryRepository accessoryRepository;
    private final PhotographerRepository photographerRepository;

    @Autowired
    public AccessoryServiceImpl(AccessoryRepository accessoryRepository,
                                PhotographerRepository photographerRepository) {
        this.accessoryRepository = accessoryRepository;
        this.photographerRepository = photographerRepository;
    }

    @Override
    public String importAccessoryFromXml(WrapperAccessoriesDto dtos){

        List<Photographer> allPhotographers = this.photographerRepository.findAll();
        StringBuilder builder = new StringBuilder();

        for (ImportAccessoriesFromXmlDto dto : dtos.getAccessories()) {
            Accessory accessory = ModelParser.getInstance().map(dto, Accessory.class);
            accessory.setOwner(allPhotographers.get(
                    RandomGenerator.getInstance().nextInt(allPhotographers.size())
            ));
            this.accessoryRepository.saveAndFlush(accessory);
            builder.append(String.format("Successfully imported %s", accessory.getName()))
                    .append(System.lineSeparator());

        }
        return builder.toString().trim();

    }
}
