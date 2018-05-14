package app.retake.parser;

import app.retake.parser.interfaces.ModelParser;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public final class ModelParserImpl implements ModelParser {

    private static ModelMapper modelMapper;

    private ModelParserImpl() {

    }

    public static ModelMapper getInstance(){
        if(modelMapper == null){
            modelMapper = new ModelMapper();
        }
        return modelMapper;
    }
    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        modelMapper.addMappings(propertyMap);
        return this.convert(source, destinationClass);
    }
}
