package com.masdefect.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.masdefect.parser.interfaces.FileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component(value = "JSONParser")
public class JSONParser implements FileParser {


    private Gson gson;

    public JSONParser() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
    }

    @Override
    public <T> T read(Class<T> oClass, String fileContent) throws IOException {
        T object;
        object = this.gson.fromJson(fileContent, oClass);
        return object;
    }

    @Override
    public <T> String write(T object) throws IOException {
        String content;
        content = this.gson.toJson(object);
        return content;
    }


}
