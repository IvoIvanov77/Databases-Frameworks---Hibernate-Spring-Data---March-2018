package app.retake.services.impl;


import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.models.AnimalAid;
import app.retake.parser.ModelParserImpl;
import app.retake.parser.ValidationUtil;
import app.retake.repositories.AnimalAidRepository;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalAidServiceImpl implements AnimalAidService {

    private final AnimalAidRepository animalAidRepository;

    @Autowired
    public AnimalAidServiceImpl(AnimalAidRepository animalAidRepository) {
        this.animalAidRepository = animalAidRepository;
    }

    @Override
    public void create(AnimalAidJSONImportDTO dto) {
        AnimalAid animalAid = ModelParserImpl.getInstance().map(dto, AnimalAid.class);

        if(ValidationUtil.isValid(animalAid)){
            this.animalAidRepository.saveAndFlush(animalAid);
        }else {
            throw new IllegalArgumentException("Error: Invalid data.");
        }
    }
}
