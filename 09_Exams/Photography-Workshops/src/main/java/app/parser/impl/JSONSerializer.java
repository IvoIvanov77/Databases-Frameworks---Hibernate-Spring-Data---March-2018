package app.parser.impl;

import app.parser.api.FileIO;
import app.parser.api.Serializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by User on 23.7.2017 г..
 */
@Component(value = "JSONParser")
public class JSONSerializer implements Serializer {

    private Gson gson;

    public JSONSerializer() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .serializeNulls()
                .create();
    }

    @Override
    public <T> T deserialize(Class<T> className, String fileContent) {
        T object;
        object = this.gson.fromJson(fileContent, className);
        return object;
    }

    @Override
    public <T> void serialize(T t, String fileName) {

    }

    @Override
    public <T> String serialize(T t) {
        String content;
        content = this.gson.toJson(t);
        return content;
    }
}
