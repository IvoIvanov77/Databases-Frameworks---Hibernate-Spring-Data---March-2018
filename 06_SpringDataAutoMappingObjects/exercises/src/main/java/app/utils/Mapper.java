package app.utils;

import org.modelmapper.ModelMapper;

public final class Mapper {

    private static ModelMapper modelMapper;

    private Mapper() {
    }

    public static ModelMapper getInstance(){
        if(modelMapper == null){
            modelMapper = new ModelMapper();
        }
        return modelMapper;
    }
}
