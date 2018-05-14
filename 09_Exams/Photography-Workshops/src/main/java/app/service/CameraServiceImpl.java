package app.service;

import app.model.dtos.json_import.ImportCameraFromJsonDto;
import app.model.entities.BasicCamera;
import app.model.entities.DSLRCamera;
import app.model.entities.MirrorLessCamera;
import app.repositories.CameraRepository;
import app.service.contracts.CameraService;
import app.utils.DataValidator;
import app.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CameraServiceImpl implements CameraService {

    private final CameraRepository cameraRepository;

    @Autowired
    public CameraServiceImpl(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    @Override
    public String importCameraFromJson(ImportCameraFromJsonDto dto){

        if(DataValidator.isValid(dto)){
            BasicCamera camera = this.convertDto(dto);

            if(DataValidator.isValid(camera)){
                this.cameraRepository.saveAndFlush(camera);
                return String.format("Successfully imported %s %s %s",
                        dto.getType(), dto.getMake(), dto.getModel());
            }
        }

        return "Error. Invalid data provided";

    }

    private BasicCamera convertDto(ImportCameraFromJsonDto dto){
        switch (dto.getType()){
            case "DSLR" :
                return ModelParser.getInstance().map(dto, DSLRCamera.class);
            case "Mirrorless" :
                return ModelParser.getInstance().map(dto, MirrorLessCamera.class);
            default: throw new IllegalArgumentException("Invalid camera type");
        }
    }
}
