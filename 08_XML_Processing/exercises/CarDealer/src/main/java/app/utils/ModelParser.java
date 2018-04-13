package app.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelParser {

    private static ModelMapper modelMapper;

    public static ModelMapper getInstance(){
        if(modelMapper == null){
            modelMapper = new ModelMapper();
        }

        return modelMapper;
    }
}
