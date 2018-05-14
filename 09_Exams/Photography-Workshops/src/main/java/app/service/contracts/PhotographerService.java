package app.service.contracts;

import app.model.dtos.json_export.ExportPhotographersToJsonDto;
import app.model.dtos.json_import.ImportPhotographerFromJsonDto;
import app.model.dtos.xml_export.ExportPhotographersToXmlDto;

import java.util.List;

public interface PhotographerService {
    String importPhotographerFromJson(ImportPhotographerFromJsonDto[] dto);

    List<ExportPhotographersToJsonDto> getOrderedPhotographers();

    List<ExportPhotographersToXmlDto> getPhotographersWithSameCameraMake();
}
