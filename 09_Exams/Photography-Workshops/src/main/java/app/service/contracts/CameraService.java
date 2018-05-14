package app.service.contracts;


import app.model.dtos.json_import.ImportCameraFromJsonDto;

public interface CameraService {


    String importCameraFromJson(ImportCameraFromJsonDto dto);
}
