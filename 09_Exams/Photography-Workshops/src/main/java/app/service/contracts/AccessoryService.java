package app.service.contracts;


import app.model.dtos.xml_import.WrapperAccessoriesDto;

public interface AccessoryService {
    String importAccessoryFromXml(WrapperAccessoriesDto dtos);
}
