package org.softuni.mostwanted.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component(value = "JSONParser")
public class JSONParser implements Parser {

    private Gson gson;

    public JSONParser() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .serializeNulls()
                .create();
    }

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException {
        T object;
        object = this.gson.fromJson(fileContent, objectClass);
        return object;
    }

    @Override
    public <T> String write(T object) throws IOException, JAXBException {
        String content;
        content = this.gson.toJson(object);
        return content;
    }
}
