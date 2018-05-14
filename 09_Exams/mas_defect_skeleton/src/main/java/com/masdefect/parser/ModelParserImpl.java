package com.masdefect.parser;

import com.masdefect.parser.interfaces.ModelParser;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ModelParserImpl implements ModelParser {

    private ModelMapper modelMapper;

    public ModelParserImpl() {
        this.modelMapper = new ModelMapper();
    }
    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        D object;
        object = this.modelMapper.map(source, destinationClass);
        return object;
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        D convertedObject;
        this.modelMapper.addMappings(propertyMap);
        convertedObject = this.modelMapper.map(source, destinationClass);
        return convertedObject;
    }
}
