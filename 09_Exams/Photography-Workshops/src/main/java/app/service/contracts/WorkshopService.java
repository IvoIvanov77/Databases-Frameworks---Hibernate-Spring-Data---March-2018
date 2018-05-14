package app.service.contracts;


import app.model.dtos.xml_import.WrapperWorkshopsDto;

public interface WorkshopService {
    String importAccessoryFromXml(WrapperWorkshopsDto dtos);
}
