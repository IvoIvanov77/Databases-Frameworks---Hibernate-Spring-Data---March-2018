package app.service;

import app.model.dtos.json_export.ExportPhotographersToJsonDto;
import app.model.dtos.json_import.ImportPhotographerFromJsonDto;
import app.model.dtos.xml_export.ExportPhotographersToXmlDto;
import app.model.entities.BasicCamera;
import app.model.entities.Photographer;
import app.repositories.CameraRepository;
import app.repositories.PhotographerRepository;
import app.service.contracts.PhotographerService;
import app.utils.DataValidator;
import app.utils.ModelParser;
import app.utils.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotographerServiceImpl implements PhotographerService {

    private final PhotographerRepository photographerRepository;
    private final CameraRepository cameraRepository;



    @Autowired
    public PhotographerServiceImpl(PhotographerRepository photographerRepository, CameraRepository cameraRepository) {
        this.photographerRepository = photographerRepository;
        this.cameraRepository = cameraRepository;
    }


    @Override
    public String importPhotographerFromJson(ImportPhotographerFromJsonDto[] dtos){

        List<BasicCamera> allCameras = this.cameraRepository.findAll();
        StringBuilder builder = new StringBuilder();

        for (ImportPhotographerFromJsonDto dto : dtos) {
            Photographer photographer = ModelParser.getInstance().map(dto, Photographer.class);
            photographer.setPrimaryCamera(allCameras.get(RandomGenerator.getInstance().nextInt(allCameras.size())));
            photographer.setSecondaryCamera(allCameras.get(RandomGenerator.getInstance().nextInt(allCameras.size())));
            if(DataValidator.isValid(photographer)){
                this.photographerRepository.saveAndFlush(photographer);
                builder.append(String.format("Successfully imported %s %s",
                        photographer.getFirstName(),
                        photographer.getLastName()))
                .append(System.lineSeparator());
            }else{
                builder.append("Error. Invalid data provided")
                        .append(System.lineSeparator());
            }
        }

        return builder.toString().trim();
    }

    @Override
    public List<ExportPhotographersToJsonDto> getOrderedPhotographers(){
        return this.photographerRepository.getOrderedPhotographers();
    }

    @Override
    public List<ExportPhotographersToXmlDto> getPhotographersWithSameCameraMake(){
        return this.photographerRepository.photographersWithSameCameraMake();
    }




}
