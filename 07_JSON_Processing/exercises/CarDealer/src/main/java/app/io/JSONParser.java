package app.io;

import app.io.contracts.Parser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JSONParser implements Parser{

    private Gson gson;


    public JSONParser() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
    }

    @Override
    public <T> T importJson(Class<T> oClass, String content){
        return this.gson.fromJson(content, oClass);
    }

    @Override
    public <T> String exportJson(T object)  {
        String content = this.gson.toJson(object);
        return content;
    }
}
